import java.awt.Color;

/**
 * An image filter to create a solarization effect.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class SolarizeFilter extends Filter
{
    /**
     * Constructor for objects of class Solarize.
     * @param name The name of the filter.
     */
    public SolarizeFilter(String name)
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
                int red = pix.getRed();
                if(red <= 127) {
                    red = 255 - red;
                }
                int green = pix.getGreen();
                if(green <= 127) {
                    green = 255 - green;
                }
                int blue = pix.getBlue();
                if(blue <= 127) {
                    blue = 255 - blue;
                }
                image.setPixel(x, y, new Color(red, green, blue));
            }
        }
    }

}
