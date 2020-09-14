/**
 * 11/19/04     1.0 moved to LGPL. 
 *-----------------------------------------------------------------------
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU Library General Public License as published
 *   by the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this program; if not, write to the Free Software
 *   Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *----------------------------------------------------------------------
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

/**
 * Play music files.
 * This class is a modified version of javazoom.jl.player.advanced.AdvancedPlayer,
 * which is part of the javazoom JLayer library.
 * The main modifications consist of:
 *     + Restriction to playing files rather than streams.
 *     + Pre-reading of the audio file to determine its length in frames.
 * These modifications permit arbitrary seek operations.
 * 
 * Modifications by David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 * This class is not suitable for playing streams as a file is read
 * completely before playing.
 */
public class MusicFilePlayer
{
    // The MPEG audio bitstream.
    private Bitstream bitstream;
    // The MPEG audio decoder.
    private Decoder decoder;
    // The AudioDevice the audio samples are written to.
    private AudioDevice audio;
    // Whether currently playing.
    private boolean playing = false;
    // The file being played.
    private String filename;
    
    // The number of frames.
    private int frameCount;
    // The current frame number.
    private int frameNumber;
    // The position to resume, if any.
    private int resumePosition;

    /**
     * Creates a new MusicFilePlayer instance.
     * @param filename The file to be played.
     */
    public MusicFilePlayer(String filename) throws JavaLayerException
    {
        this.filename = filename;
                
        openAudio();
 
        frameCount = getFrameCount(filename);
        
        // Open a fresh bitstream following the frame count.
        openBitstream(filename);
        
        frameNumber = 0;
        resumePosition = -1;  

    }

    /**
     * Play the whole file.
     */
    public void play() throws JavaLayerException
    {
        playFrames(0, frameCount);
    }

    /**
     * Plays a number of MPEG audio frames.
     *
     * @param frames    The number of frames to play.
     * @return  true if the last frame was played, or false if there are
     *          more frames.
     */
    public boolean play(int frames) throws JavaLayerException
    {
        return playFrames(frameNumber, frameNumber + frames);

    }

    /**
     * Plays a range of MPEG audio frames
     * @param start The first frame to play
     * @param end       The last frame to play
     * @return true if the last frame was played, or false if there are more frames.
     */
    public boolean play(int start, int end) throws JavaLayerException
    {
        return playFrames(start, start + end);
    }
    
    /**
     * Play from the given position to the end.
     * @param start The first frame to play.
     * @return true if the last frame was played, or false if there are more frames.
     */
    public boolean playFrom(int start) throws JavaLayerException
    {
        return playFrames(start, frameCount);
    }
    
    /**
     * Get the length of the file (in frames).
     * @return The file length, in frames.
     */
    public int getLength()
    {
        return frameCount;
    }
    
    /**
     * Get the current playing position (in frames).
     * @return The current frame number.
     */
    public int getPosition()
    {
        return frameNumber;
    }
    
    /**
     * Set the playing position (in frames).
     * Playing does not start until resume() is called.
     * @param position The playing position.
     */
    public void setPosition(int position) throws JavaLayerException
    {
        pause();
        resumePosition = position;
    }
    
    
    /**
     * Pause the playing.
     */
    public void pause() throws JavaLayerException
    {
        synchronized(this) {
            playing = false;
            resumePosition = frameNumber;
        }
    }
    
    /**
     * Resume the playing.
     */
    public void resume() throws JavaLayerException
    {
        if(!playing) {
            int start;
            if(resumePosition >= 0) {
                start = resumePosition;
            }
            else {
                start = frameNumber;
            }
            resumePosition = -1;
            playFrames(start, frameCount);
        }
    }
    
    /**
     * Return the current frame number.
     * @return The number of the last frame played, or -1 if nothing played yet.
     */
    public int getFrameNumber()
    {
        return frameNumber;
    }
    
    /**
     * Play the number of frames left.
     * @return true If finished for any reason, false if paused.
     */
    private boolean playFrames(int start, int end) throws JavaLayerException
    {
        // Clear any resumption position.
        resumePosition = -1;
        
        if(end > frameCount) {
            end = frameCount;
        }
        
        // Make sure the player is in the correct position in the input.
        synchronized(this) {
            moveTo(start);
            playing = true;
        }

        // Play until finished, paused, or a problem.
        boolean ok = true;
        while (frameNumber < end && playing && ok) {
            ok = decodeFrame();
            if(ok) {
                frameNumber++;
            }                    
        }

        // Stopped for some reason.
        synchronized(this) {
            playing = false;
            // last frame, ensure all data flushed to the audio device.
            AudioDevice out = audio;
            if (out != null) {
                out.flush();
            }
        }
        return ok;
    }
    
    /**
     * Set the playing position.
     * @param position (in frames)
     */
    private void moveTo(int position) throws JavaLayerException
    {
        if(position < frameNumber) {
            synchronized(this) {
                // Already played too far.
                if(bitstream != null) {
                    try {
                        bitstream.close();
                    }
                    catch (BitstreamException ex) {
                    }
                }
                if(audio != null) {
                    audio.close();
                }
                openAudio();
                openBitstream(filename);
                frameNumber = 0;
            }
        }
        
        while(frameNumber < position) {
            skipFrame();
            frameNumber++;
        }            
    }

    /**
     * Cloases this player. Any audio currently playing is stopped
     * immediately.
     */
    public void close()
    {
        synchronized(this) {
            if (audio != null) {
                AudioDevice out = audio;
                audio = null;
                // this may fail, so ensure object state is set up before
                // calling this method.
                out.close();
                try {
                    bitstream.close();
                }
                catch (BitstreamException ex) {
                }
                bitstream = null;
                decoder = null;
            }
        }
    }

    /**
     * Decodes a single frame.
     *
     * @return true if there are no more frames to decode, false otherwise.
     */
    protected boolean decodeFrame() throws JavaLayerException
    {
        try
        {
            synchronized (this) {
                if (audio == null) {
                    return false;
                }
    
                Header h = readFrame();
                if (h == null) {
                    return false;
                }
    
                // sample buffer set when decoder constructed
                SampleBuffer output = (SampleBuffer) decoder.decodeFrame(h, bitstream);

                if(audio != null) {
                    audio.write(output.getBuffer(), 0, output.getBufferLength());
                }
            }

            bitstream.closeFrame();
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new JavaLayerException("Exception decoding audio frame", ex);
        }
        return true;
    }


    /**
     * skips over a single frame
     * @return false    if there are no more frames to decode, true otherwise.
     */
    protected boolean skipFrame() throws JavaLayerException
    {
        Header h = readFrame();
        if (h == null) {
            return false;
        }
        frameNumber++;
        bitstream.closeFrame();
        return true;
    }

    /**
     * closes the player and notifies <code>PlaybackListener</code>
     */
    public void stop()
    {
        close();
    }
    
    /**
     * Count the number of frames in the file.
     * This can be used for positioning.
     * @param filename The file to be measured.
     * @return The number of frames.
     */
    protected int getFrameCount(String filename) throws JavaLayerException
    {
        openBitstream(filename);
        int count = 0;
        while(skipFrame()) {
            count++;
        }
        bitstream.close();
        return count;        
    }
    
    /**
     * Read a frame.
     * @return The frame read.
     */
    protected Header readFrame() throws JavaLayerException
    {
        if(audio != null) {
            return bitstream.readFrame();
        }
        else {
            return null;
        }
    }
    
    /**
     * Open an audio device.
     */
    protected void openAudio() throws JavaLayerException
    {
        audio = FactoryRegistry.systemRegistry().createAudioDevice();
        decoder = new Decoder();
        audio.open(decoder);
    }
    
    /**
     * Open a BitStream for the given file.
     * @param filename The file to be opened.
     * @throws IOException If the file cannot be opened.
     */
    protected void openBitstream(String filename)
        throws JavaLayerException
    {
        try {
            bitstream = new Bitstream(new BufferedInputStream(
                        new FileInputStream(filename)));
        }
        catch(java.io.IOException ex) {
            throw new JavaLayerException(ex.getMessage(), ex);
        }
                    
    }
}



