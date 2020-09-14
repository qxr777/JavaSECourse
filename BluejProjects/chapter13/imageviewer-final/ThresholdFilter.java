import java.awt.Color;

/**
 * An three-level gray-based threshold filter.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class ThresholdFilter extends Filter
{
	/**
	 * Constructor for objects of class ThresholdFilter.
     * @param name The name of the filter.
	 */
	public ThresholdFilter(String name)
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
                Color pixel = image.getPixel(x, y);
                int brightness = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                if(brightness <= 85) {
                    image.setPixel(x, y, Color.BLACK);
                }
                else if(brightness <= 170) {
                    image.setPixel(x, y, Color.GRAY);
                }
                else {
                    image.setPixel(x, y, Color.WHITE);
                }
            }
        }
    }
}
