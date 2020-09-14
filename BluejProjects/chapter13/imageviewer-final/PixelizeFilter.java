import java.awt.Color;

/**
 * An image filter to create a pixelization effect, like an enlarged
 * low-resolution digital image.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class PixelizeFilter extends Filter
{
    /**
     * Constructor for objects of class PixelizeFilter.
     * @param name The name of the filter.
     */
    public PixelizeFilter(String name)
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
        final int PIXEL_SIZE = 5;
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int y = 0; y < height; y += PIXEL_SIZE) {
            for(int x = 0; x < width; x += PIXEL_SIZE) {
                Color pix = image.getPixel(x, y);
                for(int dy = y; dy < y + PIXEL_SIZE; dy++) {
                    for(int dx = x; dx < x + PIXEL_SIZE; dx++) {
                        if( dx < width && dy < height )
                            image.setPixel(dx, dy, pix);
                    }
                }
            }
        }
    }
}
