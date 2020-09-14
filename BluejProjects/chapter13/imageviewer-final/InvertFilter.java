import java.awt.Color;

/**
 * An image filter to invert colors.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class InvertFilter extends Filter
{
    /**
     * Constructor for objects of class InvertFilter.
     * @param name The name of the filter.
     */
    public InvertFilter(String name)
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
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pix = image.getPixel(x, y);
                image.setPixel(x, y, new Color(255 - pix.getRed(),
                                               255 - pix.getGreen(),
                                               255 - pix.getBlue()));
            }
        }
    }
}
