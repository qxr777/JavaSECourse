import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. A single passenger is created, and a pickup
 * requested. As the simulation is run, the passenger
 * should be picked up and then taken to their destination.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Demo
{
    private List<Actor> actors;

    /**
     * Constructor for objects of class Demo
     */
    public Demo()
    {
        actors = new LinkedList<>();
        reset();
    }
    
    /**
     * Run the demo for a fixed number of steps.
     */
    public void run()
    {
        for(int step = 0; step < 100; step++) {
            step();
        }
    }

    /**
     * Run the demo for one step by requesting
     * all actors to act.
     */
    public void step()
    {
        for(Actor actor : actors) {
            actor.act();
        }
    }
    
    /**
     * Reset the demo to a starting point.
     * A single taxi is created, and a pickup is
     * requested for a single passenger.
     * @throws IllegalStateException If a pickup cannot be found
     */
    public void reset()
    {
        actors.clear();
        TaxiCompany company = new TaxiCompany();
        Taxi taxi = new Taxi(company, new Location(10, 10));
        List<Vehicle> vehicles = company.getVehicles();
        vehicles.add(taxi);
        actors.addAll(vehicles);
        
        Passenger passenger = new Passenger(new Location(0, 0),
                                            new Location(10, 20));
        if(!company.requestPickup(passenger)) {
            throw new IllegalStateException("Failed to find a pickup.");
        }
    }
}
