import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Model a passenger wishing to get from one
 * location to another.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Passenger implements DrawableItem
{
    private Location pickup;
    private Location destination;
    private Image image;

    /**
     * Constructor for objects of class Passenger
     * @param pickup The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @throws NullPointerException If either location is null.
     */
    public Passenger(Location pickup, Location destination)
    {
        if(pickup == null) {
            throw new NullPointerException("Pickup location");
        }
        if(destination == null) {
            throw new NullPointerException("Destination location");
        }
        this.pickup = pickup;
        this.destination = destination;
        // Load the image used to represent a person.
        image = new ImageIcon(getClass().getResource(
                              "images/person.jpg")).getImage();
    }
    
    /**
     * @return A string representation of this person.
     */
    public String toString()
    {
        return "Passenger travelling from " +
               pickup + " to " + destination;
    }

    /**
     * @return The image to be displayed on a GUI.
     */
    public Image getImage()
    {
        return image;
    }
    
    /**
     * @return The passenger's pickup location.
     */
    public Location getLocation()
    {
        return pickup;
    }

    /**
     * @return The pickup location.
     */
    public Location getPickupLocation()
    {
        return pickup;
    }
    
    /**
     * @return The destination location.
     */
    public Location getDestination()
    {
        return destination;
    }
}
