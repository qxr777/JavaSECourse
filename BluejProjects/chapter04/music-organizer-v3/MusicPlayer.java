import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * Provide basic playing of MP3 files via the javazoom library.
 * See http://www.javazoom.net/
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class MusicPlayer
{
    // The current player. It might be null.
    private AdvancedPlayer player;
    
    /**
     * Constructor for objects of class MusicFilePlayer
     */
    public MusicPlayer()
    {
        player = null;
    }
    
    /**
     * Play a part of the given file.
     * The method returns once it has finished playing.
     * @param filename The file to be played.
     */
    public void playSample(String filename)
    {
        try {
            setupPlayer(filename);
            player.play(500);
        }
        catch(JavaLayerException e) {
            reportProblem(filename);
        }
        finally {
            killPlayer();
        }
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
            Thread playerThread = new Thread() {
                public void run()
                {
                    try {
                        player.play(5000);
                    }
                    catch(JavaLayerException e) {
                        reportProblem(filename);
                    }
                    finally {
                        killPlayer();
                    }
                }
            };
            playerThread.start();
        }
        catch (Exception ex) {
            reportProblem(filename);
        }
    }
    
    public void stop()
    {
        killPlayer();
    }
    
    /**
     * Set up the player ready to play the given file.
     * @param filename The name of the file to play.
     */
    private void setupPlayer(String filename)
    {
        try {
            InputStream is = getInputStream(filename);
            player = new AdvancedPlayer(is, createAudioDevice());
        }
        catch (IOException e) {
            reportProblem(filename);
            killPlayer();
        }
        catch(JavaLayerException e) {
            reportProblem(filename);
            killPlayer();
        }
    }

    /**
     * Return an InputStream for the given file.
     * @param filename The file to be opened.
     * @throws IOException If the file cannot be opened.
     * @return An input stream for the file.
     */
    private InputStream getInputStream(String filename)
        throws IOException
    {
        return new BufferedInputStream(
                    new FileInputStream(filename));
    }

    /**
     * Create an audio device.
     * @throws JavaLayerException if the device cannot be created.
     * @return An audio device.
     */
    private AudioDevice createAudioDevice()
        throws JavaLayerException
    {
        return FactoryRegistry.systemRegistry().createAudioDevice();
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
            }
        }
    }
    
    /**
     * Report a problem playing the given file.
     * @param filename The file being played.
     */
    private void reportProblem(String filename)
    {
        System.out.println("There was a problem playing: " + filename);
    }

}
