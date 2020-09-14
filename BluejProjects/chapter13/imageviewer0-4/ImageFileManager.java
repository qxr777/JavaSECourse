import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

/**
 * ImageFileManager is a small utility class with static methods to load
 * and save images.
 * 
 * The files on disk can be in JPG or PNG image format. For files written
 * by this class, the format is determined by the constant IMAGE_FORMAT.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class ImageFileManager
{
    // A constant for the image format that this writer uses for writing.
    // Available formats are "jpg" and "png".
    private static final String IMAGE_FORMAT = "jpg";
    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
    
    /**
     * Open a file chooser and let the user select an image file in the file
     * system. Then read an image file from disk and return it as an image. This method
     * can read JPG and PNG file formats. In case of any problem (e.g the file does
     * not exist, is in an undecodable format, or any other read error) this method
     * returns null.
     * 
     * @return       The image object or null is it was not a valid image file.
     */
    public static OFImage getImage()
    {
        int returnVal = fileChooser.showOpenDialog(null);

        if(returnVal != JFileChooser.APPROVE_OPTION) {
            return null;  // cancelled
        }
        File selectedFile = fileChooser.getSelectedFile();
        return loadImage(selectedFile);
    }

    /**
     * Read an image file from disk and return it as a BufferedImage. This method
     * can read JPG and PNG file formats. In case of any problem (e.g the file does
     * not exist, is in an undecodable format, or any other read error) this method
     * returns null.
     * 
     * @param imageFile  The image file to be loaded.
     * @return           The image object or null if it was not a valid image file.
     */
    public static OFImage loadImage(File imageFile)
    {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if(image == null || (image.getWidth(null) < 0)) {
                // we could not load the image - probably invalid file format
                return null;
            }
            return new OFImage(image);
        }
        catch(IOException exc) {
            return null;
        }
    }

    /**
     * Write an image file to disk. The file format is JPG. In case of any problem
     * the method just silently returns.
     * 
     * @param image  The image to be saved.
     * @param file   The file to save to.
     */
    public static void saveImage(OFImage image, File file)
    {
        try {
            ImageIO.write(image, IMAGE_FORMAT, file);
        }
        catch(IOException exc) {
            return;
        }
    }
}
