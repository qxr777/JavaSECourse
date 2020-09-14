import java.awt.*;
import javax.swing.*;

/**
 * Illustrate the layout style of a BorderLayout.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class BorderLayoutExample
{
    private JFrame frame;

    /**
     * Constructor for objects of class BorderLayoutExample
     */
    public BorderLayoutExample()
    {
        makeFrame();
    }

    /**
     * Place five components in the available regions.
     */
    private void makeFrame()
    {
        frame = new JFrame("BorderLayout Example");
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JButton("north"), BorderLayout.NORTH);
        contentPane.add(new JButton("south"), BorderLayout.SOUTH);
        contentPane.add(new JButton("center"), BorderLayout.CENTER);
        contentPane.add(new JButton("west"), BorderLayout.WEST);
        contentPane.add(new JButton("east"), BorderLayout.EAST);
        
        frame.pack();
        frame.setVisible(true);
    }
}
