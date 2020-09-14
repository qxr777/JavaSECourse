import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A taxi is able to carry a single passenger.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Taxi extends Vehicle implements DrawableItem
{
    private Passenger passenger;
    // Maintain separate images for when the taxi is empty
    // and full.
    private Image emptyImage, passengerImage;
    
    /**
     * Constructor for objects of class Taxi
     * @param company The taxi company. Must not be null.
     * @param location The vehicle's starting point. Must not be null.
     * @throws NullPointerException If company or location is null.
     */
    public Taxi(TaxiCompany company, Location location)
    {
        super(company, location);
    // Load the two images.
        emptyImage = new ImageIcon(getClass().getResource(
                                "images/taxi.jpg")).getImage();

        passengerImage = new ImageIcon(getClass().getResource(
                                "images/taxi+person.jpg")).getImage();
    }
    
    /**
     * Move towards the target location if we have one.
     * Otherwise record that we are idle.
     */
    public void act()
    {
        Location target = getTargetLocation();
        if(target != null) {
            // Find where to move to next.
            Location next = getLocation().nextLocation(target);
            setLocation(next);
            if(next.equals(target)) {
                if(passenger != null) {
                    notifyPassengerArrival(passenger);
                    offloadPassenger();
                }
                else {
                    notifyPickupArrival();
                }
            }
        }
        else {
            incrementIdleCount();
        }
    }

    /**
     * Is the taxi free?
     * @return Whether or not this taxi is free.
     */
    public boolean isFree()
    {
        return getTargetLocation() == null && passenger == null;
    }
    
    /**
     * Receive a pickup location. This becomes the
     * target location.
     * @param location The pickup location.
     */
    public void setPickupLocation(Location location)
    {
        setTargetLocation(location);
    }
    
    /**
     * Receive a passenger.
     * Set their destination as the target location.
     * @param passenger The passenger.
     */
    public void pickup(Passenger passenger)
    {
        this.passenger = passenger;
        setTargetLocation(passenger.getDestination());
    }

    /**
     * Offload the passenger.
     */
    public void offloadPassenger()
    {
        passenger = null;
        clearTargetLocation();
    }
    
    /**
     * Return an image that describes our state:
     * either empty or carrying a passenger.
     */
    public Image getImage()
    {
        if(passenger != null) {
            return passengerImage;
        }
        else {
            return emptyImage;
        }
    }

    /**
     * Return details of the taxi, such as where it is.
     * @return A string representation of the taxi.
     */
    public String toString()
    {
        return "Taxi at " + getLocation();
    }
}
