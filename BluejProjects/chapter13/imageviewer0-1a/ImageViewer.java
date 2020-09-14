import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ImageViewer is the main class of the image viewer application. It builds
 * and displays the application GUI and initialises all other components.
 * 
 * To start the application, create an object of this class.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 0.1a
 */
public class ImageViewer extends JFrame
{
    
    /**
     * Create an ImageViewer show it on screen.
     */
    public ImageViewer()
    {
        super("ImageViewer");
        makeFrame();
    }
    
    // ---- swing stuff to build the frame and all its components ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        Container contentPane = getContentPane();
        
        JLabel label = new JLabel("I am a label. I can display some text.");
        contentPane.add(label);

        pack();
        setVisible(true);
    }
}
