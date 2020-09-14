import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
 * Model the operation of a taxi company, operating different
 * types of vehicle. This version operates a single taxi.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TaxiCompany  
{
    // The vehicles operated by the company.
    private List<Vehicle> vehicles;
    // The associations between vehicles and the passengers
    // they are to pick up.
    private Map<Vehicle, Passenger> assignments;

    /**
     * Constructor for objects of class TaxiCompany
     */
    public TaxiCompany()
    {
        vehicles = new LinkedList<>();
        assignments = new HashMap<>();
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
            assignments.put(vehicle, passenger);
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
     * @throws MissingPassengerException If there is no passenger waiting.
     */
    public void arrivedAtPickup(Vehicle vehicle)
    {
        Passenger passenger = assignments.remove(vehicle);
        if(passenger == null) {
            throw new MissingPassengerException(vehicle);
        }
        System.out.println(vehicle + " picks up " + passenger);
        vehicle.pickup(passenger);
    }
    
    /**
     * A vehicle has arrived at a passenger's destination.
     * @param vehicle The vehicle at the destination.
     * @param passenger The passenger being dropped off.
     */
    public void arrivedAtDestination(Vehicle vehicle,
                                     Passenger passenger)
    {
        System.out.println(vehicle + " offloads " + passenger);
    }
    
    /**
     * @return The list of vehicles.
     */
    public List<Vehicle> getVehicles()
    {
        return vehicles;
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
