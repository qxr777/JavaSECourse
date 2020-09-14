import java.awt.Color;

/**
 * An image filter to create an effect similar to a fisheye camera lens.
 * (Works especially well on portraits.)
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class FishEyeFilter extends Filter
{
    // constants:
    private final static int SCALE = 20;   // this defines the strenght of the filter
    private final static double TWO_PI = 2 * Math.PI;

    /**
     * Constructor for objects of class LensFilter.
     * @param name The name of the filter.
     */
    public FishEyeFilter(String name)
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
        int height = image.getHeight();
        int width = image.getWidth();
        OFImage original = new OFImage(image);

        int[] xa = computeXArray(width);
        int[] ya = computeYArray(height);
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                image.setPixel(x, y, original.getPixel(x + xa[x], y + ya[y]));
            }
        }
    }

    /**
     * Compute and return an array of horizontal offsets for each pixel column.
     * These can then be applied as the horizontal offset for each pixel.
     */
    private int[] computeXArray(int width)
    {
        int[] xArray = new int[width];
        
        for(int i=0; i < width; i++) {
            xArray[i] = (int)(Math.sin( ((double)i / width) * TWO_PI) * SCALE);
        }
        return xArray;
    }

    /**
     * Compute and return an array of vertical offsets for each pixel row.
     * These can then be applied as the vertical offset for each pixel.
     */
    private int[] computeYArray(int height)
    {
        int[] yArray = new int[height];
        
        for(int i=0; i < height; i++) {
            yArray[i] = (int)(Math.sin( ((double)i / height) * TWO_PI) * SCALE);
        }
        return yArray;
    }
}
