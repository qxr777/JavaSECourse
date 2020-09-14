import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Model the operation of a taxi company, operating different
 * types of vehicle.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TaxiCompany  
{
    private List<Vehicle> vehicles;

    /**
     * Constructor for objects of class TaxiCompany
     */
    public TaxiCompany()
    {
        vehicles = new LinkedList<>();
    }

    /**
     * Request a pickup for the given passenger.
     * @param passenger The passenger requesting a pickup.
     * @return Whether a free vehicle is available.
     */
    public boolean requestPickup(Passenger passenger)
    {
        Vehicle vehicle = scheduleVehicle();
        if(vehicle != null) {
            vehicle.setPickupLocation(passenger.getPickupLocation());
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * A vehicle has arrived at a pickup point.
     * @param vehicle The vehicle at the pickup point.
     */
    public void arrivedAtPickup(Vehicle vehicle)
    {
    }
    
    /**
     * A vehicle has arrived at a passenger's destination.
     * @param vehicle The vehicle at the destination.
     * @param passenger The passenger being dropped off.
     */
    public void arrivedAtDestination(Vehicle vehicle,
                                     Passenger passenger)
    {
    }
    
    /**
     * Find a free vehicle, if any.
     * @return A free vehicle, or null if there is none.
     */
    private Vehicle scheduleVehicle()
    {
        Iterator<Vehicle> it = vehicles.iterator();
        while(it.hasNext()) {
            Vehicle vehicle = it.next();
            if(vehicle.isFree()) {
                return vehicle;
            }
        }
        return null;
    }
}
