import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * A GUI for the environment, with runtime controls.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version  2016.02.29
 */
public class EnvironmentView extends JFrame
{
    // The longest delay for the animation, in milliseconds.
    private static final int LONGEST_DELAY = 1000;
    // Colors for the different cell states.
    private static final Color[] colors = {
        Color.WHITE, // Alive
        new Color(68, 100, 129), // Dead
        new Color(204, 196, 72),  // Dying
    };
    
    private GridView view;
    private final Environment env;
    private boolean running;
    private int delay;
    
    /**
     * Constructor for objects of class EnvironmentView
     * @param env
     */
    public EnvironmentView(Environment env, int rows, int cols)
    {
        super("2D Cellular Automaton");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(20, 20);
        this.env = env;
        this.running = false;
        setDelay(50);
        setupControls();
        setupGrid(rows, cols);
        pack();
        setVisible(true);
    }

    /**
     * Setup a new environment of the given size.
     * @param rows The number of rows.
     * @param cols The number of cols;
     */
    private void setupGrid(int rows, int cols)
    {
        Container contents = getContentPane();
        view = new GridView(rows, cols);
        contents.add(view, BorderLayout.CENTER);
    }
    
    /**
     * Show the states of the cells.
     */
    public void showCells()
    {
        Cell[][] cells = env.getCells();
        if(!isVisible()) {
            setVisible(true);
        }
        
        view.preparePaint();
        for(int row = 0; row < cells.length; row++) {
            Cell[] cellRow = cells[row];
            int numCols = cellRow.length;
            for(int col = 0; col < numCols; col++) {
                int state = cellRow[col].getState();
                view.drawMark(col, row, colors[state]);
            }
        }
        
        view.repaint();
    }
    
    /**
     * Set up the animation controls.
     */
    private void setupControls()
    {
        // Continuous running.
        final JButton run = new JButton("Run");
        run.addActionListener(e -> {
            if(!running) {
                running = true;
                try {
                    new Runner().execute();
                }
                catch(Exception ex) {
                }
            }
        });
        
        // Single stepping.
        final JButton step = new JButton("Step");
        step.addActionListener(e -> {
            running = false;
            env.step();
            showCells();
        });
        
        // Pause the animation.
        final JButton pause = new JButton("Pause");
        pause.addActionListener(e -> running = false);
        
        // Reset of the environment
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> {
            running = false;
            env.reset();
            showCells();
        });
        
        // Randomize the environment.
        final JButton randomize = new JButton("Random");
        randomize.addActionListener(e -> {
            running = false;
            env.randomize();
            showCells();
        });
        
        Container contents = getContentPane();
        
        // A speed controller.
        final JSlider speedSlider = new JSlider(0, 100);
        speedSlider.addChangeListener(e -> {
            setDelay(speedSlider.getValue());
        });
        Container speedPanel = new JPanel();
        speedPanel.setLayout(new GridLayout(2, 1));
        speedPanel.add(new JLabel("Animation Speed", SwingConstants.CENTER));
        speedPanel.add(speedSlider);
        contents.add(speedPanel, BorderLayout.NORTH);
        
        // Place the button controls.
        JPanel controls = new JPanel();
        controls.add(run);
        controls.add(step);
        controls.add(pause);
        controls.add(reset);
        controls.add(randomize);
        
        contents.add(controls, BorderLayout.SOUTH);
    }

    
    /**
     * Set the animation delay.
     * @param speedPercentage (100-speedPercentage) as a percentage of the LONGEST_DELAY.
     */
    private void setDelay(int speedPercentage)
    {
        delay = (int) ((100.0 - speedPercentage) * LONGEST_DELAY / 100);
    }
    
    /**
     * Provide stepping of the animation.
     */
    private class Runner extends SwingWorker<Boolean, Void>
    {
        @Override
        /**
         * Repeatedly single-step the environment as long
         * as the animation is running.
         */
        public Boolean doInBackground()
        {
            while(running) {
                env.step();
                showCells();
                try {
                    Thread.sleep(delay);
                }
                catch(InterruptedException e) {
                }
            }
            return true;
        }
    }


    /**
     * Provide a graphical view of a rectangular grid.
     */
    @SuppressWarnings("serial")
    private class GridView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 10;

        private final int gridWidth, gridHeight;
        private int xScale, yScale;
        private Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new GridView component.
         */
        public GridView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {
                size = getSize();
                fieldImage = view.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this field in a given color.
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        @Override
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
