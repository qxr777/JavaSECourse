import java.awt.Color;

import java.util.List;
import java.util.ArrayList;

/**
 * An image filter to detect edges and highlight them, a bit like 
 * a colored pencil drawing.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class EdgeFilter extends Filter
{
    private static final int TOLERANCE = 20;
    
    private OFImage original;
    private int width;
    private int height;

    /**
     * Constructor for objects of class EdgeFilter.
     * @param name The name of the filter.
     */
    public EdgeFilter(String name)
    {
        super(name);
    }

    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
        original = new OFImage(image);
        width = original.getWidth();
        height = original.getHeight();
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                image.setPixel(x, y, edge(x, y));
            }
        }
    }

    /**
     * Return a new color that is the smoothed color of a given
     * position. The "smoothed color" is the color value that is the
     * average of this pixel and all the adjacent pixels.
     * @param xpos The x position of the pixel.
     * @param ypos The y position of the pixel.
     * @return The smoothed color.
     */
    private Color edge(int xpos, int ypos)
    {
        List<Color> pixels = new ArrayList<>(9);
        
        for(int y = ypos-1; y <= ypos+1; y++) {
            for(int x = xpos-1; x <= xpos+1; x++) {
                if( x >= 0 && x < width && y >= 0 && y < height ) {
                    pixels.add(original.getPixel(x, y));
                }
            }
        }

        return new Color(255 - diffRed(pixels), 255 - diffGreen(pixels), 255 - diffBlue(pixels));
    }

    /**
     * @param pixels The list of pixels to be averaged.
     * @return The average of all the red values in the given list of pixels.
     */
    private int diffRed(List<Color> pixels)
    {
        int max = 0;
        int min = 255;
        for(Color color : pixels) {
            int val = color.getRed();
            if(val > max) {
                max = val;
            }
            if(val < min) {
                min = val;
            }
        }
        int difference = max - min - TOLERANCE;
        if(difference < 0) {
            difference = 0;
        }
        return difference;
    }

    /**
     * @param pixels The list of pixels to be averaged.
     * @return The average of all the green values in the given list of pixels.
     */
    private int diffGreen(List<Color> pixels)
    {
        int max = 0;
        int min = 255;
        for(Color color : pixels) {
            int val = color.getGreen();
            if(val > max) {
                max = val;
            }
            if(val < min) {
                min = val;
            }
        }
        int difference = max - min - TOLERANCE;
        if(difference < 0) {
            difference = 0;
        }
        return difference;
    }

    /**
     * @param pixels The list of pixels to be averaged.
     * @return The average of all the blue values in the given list of pixels.
     */
    private int diffBlue(List<Color> pixels)
    {
        int max = 0;
        int min = 255;
        for(Color color : pixels) {
            int val = color.getBlue();
            if(val > max) {
                max = val;
            }
            if(val < min) {
                min = val;
            }
        }
        int difference = max - min - TOLERANCE;
        if(difference < 0) {
            difference = 0;
        }
        return difference;
    }

}
