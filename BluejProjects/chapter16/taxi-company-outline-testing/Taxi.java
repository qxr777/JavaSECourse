
/**
 * A taxi is able to carry a single passenger.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Taxi extends Vehicle
{
    /**
     * Constructor for objects of class Taxi
     * @param company The taxi company. Must not be null.
     * @param location The vehicle's starting point. Must not be null.
     * @throws NullPointerException If company or location is null.
     */
    public Taxi(TaxiCompany company, Location location)
    {
        super(company, location);
    }

    /**
     * Is the taxi free?
     * @return Whether or not this taxi is free.
     */
    public boolean isFree()
    {
        return getTargetLocation() == null;
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
        setTargetLocation(passenger.getDestination());
    }

    /**
     * Offload the passenger.
     */
    public void offloadPassenger()
    {
        clearTargetLocation();
    }
}
