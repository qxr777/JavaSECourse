import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author: Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class UserInterface
{
    private CalcEngine calc;
    private boolean showingAuthor;

    private JFrame frame;
    private JTextField display;
    private JLabel status;

    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    public UserInterface(CalcEngine engine)
    {
        calc = engine;
        showingAuthor = true;
        makeFrame();
        frame.setVisible(true);
    }

    /**
     * Set the visibility of the interface.
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    /**
     * Make the frame for the user interface.
     */
    private void makeFrame()
    {
        frame = new JFrame(calc.getTitle());
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
            addNumberButton(buttonPanel, 7);
            addNumberButton(buttonPanel, 8);
            addNumberButton(buttonPanel, 9);
            addButton(buttonPanel, "C", () -> calc.clear());
            
            addNumberButton(buttonPanel, 4);
            addNumberButton(buttonPanel, 5);
            addNumberButton(buttonPanel, 6);
            addButton(buttonPanel, "?", () -> showInfo());
            
            addNumberButton(buttonPanel, 1);
            addNumberButton(buttonPanel, 2);
            addNumberButton(buttonPanel, 3);
            buttonPanel.add(new JLabel(" "));
            
            addNumberButton(buttonPanel, 0);
            addButton(buttonPanel, "+", () -> calc.plus());
            addButton(buttonPanel, "-", () -> calc.minus());
            addButton(buttonPanel, "=", () -> calc.equals());
            
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     * @param action Action to be taken by the button.
     */
    private void addButton(Container panel, String buttonText, ButtonAction action)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> { action.act(); redisplay(); });
        panel.add(button);
    }

    /**
     * Add a number button to the button panel.
     * @param panel The panel to receive the button.
     * @param digit The single digit on the button.
     */
    private void addNumberButton(Container panel, int digit)
    {
        addButton(panel, "" + digit, () -> calc.numberPressed(digit));
    }

    /**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    private void redisplay()
    {
        display.setText("" + calc.getDisplayValue());
    }

    /**
     * Toggle the info display in the calculator's status area between the
     * author and version information.
     */
    private void showInfo()
    {
        if(showingAuthor)
            status.setText(calc.getVersion());
        else
            status.setText(calc.getAuthor());

        showingAuthor = !showingAuthor;
    }
    
    /**
     * Functional interface for button actions.
     */
    @FunctionalInterface
    private interface ButtonAction
    {
        /**
         * Act on a button press.
         */
        public void act();
    }
}
