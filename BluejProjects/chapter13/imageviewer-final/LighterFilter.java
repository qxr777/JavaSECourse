/**
 * An image filter to make the image a bit lighter.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class LighterFilter extends Filter
{
	/**
	 * Constructor for objects of class LighterFilter.
     * @param name The name of the filter.
	 */
	public LighterFilter(String name)
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
                image.setPixel(x, y, image.getPixel(x, y).brighter());
            }
        }
    }

}
