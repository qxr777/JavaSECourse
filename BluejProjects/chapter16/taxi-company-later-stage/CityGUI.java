import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.*;
import javax.swing.*;
    
/**
 * Provide a view of the vehicles and passengers in the city.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class CityGUI extends JFrame implements Actor
{
    // The dimensions of the GUI.
    public static final int CITY_VIEW_WIDTH = 600;
    public static final int CITY_VIEW_HEIGHT = 600;
    private City city;
    private CityView cityView;
    
    /**
     * Constructor for objects of class CityGUI
     * @param city The city whose state is to be displayed.
     */
    public CityGUI(City city)
    {
        this.city = city;
        cityView = new CityView(city.getWidth(), city.getHeight());
        getContentPane().add(cityView);
        setTitle("Taxiville");
        setSize(CITY_VIEW_WIDTH, CITY_VIEW_HEIGHT);
        setVisible(true);
        cityView.preparePaint();
        cityView.repaint();    
    }
    
    /**
     * Display the current state of the city.
     */
    public void act()
    {
        cityView.preparePaint();
        Iterator<Item> items = city.getItems();
        while(items.hasNext()) {
            Item item = items.next();
            if(item instanceof DrawableItem){
                DrawableItem drawable = (DrawableItem) item;
                Location location = item.getLocation();
                cityView.drawImage(location.getX(), location.getY(), drawable.getImage());
            }
        }
        cityView.repaint();    
    }
    
    /**
     * Provide a graphical view of a rectangular city. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the city.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class CityView extends JPanel
    {
        private final int VIEW_SCALING_FACTOR = 6;

        private int cityWidth, cityHeight;
        private int xScale, yScale;
        private Dimension size;
        private Graphics g;
        private Image cityImage;

        /**
         * Create a new CityView component.
         */
        public CityView(int width, int height)
        {
            cityWidth = width;
            cityHeight = height;
            setBackground(Color.white);
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(cityWidth * VIEW_SCALING_FACTOR,
                                 cityHeight * VIEW_SCALING_FACTOR);
        }
        
        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(!size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                cityImage = cityView.createImage(size.width, size.height);
                g = cityImage.getGraphics();

                xScale = size.width / cityWidth;
                if(xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }
                yScale = size.height / cityHeight;
                if(yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(Color.gray);
            for(int i = 0, x = 0; x < size.width; i++, x = i * xScale) {
                g.drawLine(x, 0, x, size.height - 1);
            }
            for(int i = 0, y = 0; y < size.height; i++, y = i * yScale) {
                g.drawLine(0, y, size.width - 1, y);
            }
        }
        
        /**
         * Draw the image for a particular item.
         */
        public void drawImage(int x, int y, Image image)
        {
            g.drawImage(image, x * xScale + 1, y * yScale + 1,
                        xScale - 1, yScale - 1, this);
        }

        /**
         * The city view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(cityImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(cityImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(cityImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
