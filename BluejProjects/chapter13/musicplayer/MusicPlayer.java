import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

/**
 * Provide basic playing of MP3 files via the javazoom library.
 * See http://www.javazoom.net/
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class MusicPlayer
{
    // The current player. It might be null.
    private MusicFilePlayer player;
    // The current file being played.
    private String filename;
    
    /**
     * Constructor for objects of class MusicPlayer
     */
    public MusicPlayer()
    {
        player = null;
        filename = "";
    }
    
    /**
     * Start playing the given audio file.
     * The method returns once the playing has been started.
     * @param filename The file to be played.
     */
    public void startPlaying(final String filename)
    {
        try {
            setupPlayer(filename);
            playFrom(0);
        }
        catch (JavaLayerException ex) {
            reportProblem();
        }
    }
    
    /**
     * Stop playing the current file.
     */
    public void stop()
    {
        killPlayer();
    }
    
    /**
     * Pause the current file.
     */
    public void pause()
    {
        if(player != null) {
            try {
                player.pause();
            }
            catch(JavaLayerException e) {
                reportProblem();
                killPlayer();
            }
        }
    }
    
    /**
     * Resume playing following a pause.
     */
    public void resume()
    {
        if(player != null) {
            Thread playerThread = new Thread() {
                public void run()
                {
                    try {
                        player.resume();
                    }
                    catch(JavaLayerException e) {
                        reportProblem();
                        killPlayer();
                    }
                }
            };
            playerThread.setPriority(Thread.MIN_PRIORITY);
            playerThread.start();
        }
    }
    
    /**
     * Seek to the given position in the current file.
     * The track will be paused as a result of this operation.
     * 
     * @param position What position in the file to move to.
     */
    public void seekTo(int position)
    {
        if(player != null && position >= 0 && position < player.getLength()) {
            // Set the player's position.
        }
            
    }
    
    /**
     * Return the length of the current music file, if any.
     * The length is in 'frames' rather than seconds, for instance.
     * 
     * @return The file length in frames.
     */
    public int getLength()
    {
        if(player != null) {
            return player.getLength();
        }
        else {
            return 0;
        }
    }
    
    /**
     * Set up the player ready to play the given file.
     * @param filename The name of the file to play.
     */
    private void setupPlayer(String filename)
    {
        try {
            if(player != null) {
                killPlayer();
            }
            this.filename = filename;
            player = new MusicFilePlayer(filename);
        }
        catch(JavaLayerException e) {
            System.out.println("Problem setting up player");
            e.printStackTrace();
            reportProblem();
            killPlayer();
        }
    }
    
    /**
     * Play from the given position.
     * @param start The starting position for playing.
     *              Must be within the current file's length.
     */
    private void playFrom(final int start) throws JavaLayerException
    {
        Thread playerThread = new Thread() {
            public void run()
            {
                try {
                    player.playFrom(start);
                }
                catch(JavaLayerException e) {
                    reportProblem();
                    killPlayer();
                }
            }
        };
        playerThread.setPriority(Thread.MIN_PRIORITY);
        playerThread.start();
    }

    /**
     * Terminate the player, if there is one.
     */
    private void killPlayer()
    {
        synchronized(this) {
            if(player != null) {
                player.stop();
                player = null;
                filename = "";
            }
        }
    }
    
    /**
     * Report a problem playing the current file.
     */
    private void reportProblem()
    {
        System.out.println("There was a problem playing: " + filename);
    }

}
