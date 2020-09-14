import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TaxiTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TaxiTest
{
    private Taxi taxi;
    private Passenger passenger;
    
    /**
     * Default constructor for test class TaxiTest
     */
    public TaxiTest()
    {
    }

    /**
     * Create a taxi.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        TaxiCompany company = new TaxiCompany();
        // Starting position for the taxi.
        Location taxiLocation = new Location(0, 0);
        // Locations for the passenger.
        Location pickup = new Location(1, 2);
        Location destination = new Location(5, 6);
        
        passenger = new Passenger(pickup, destination);
        taxi = new Taxi(company, taxiLocation);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test creation and the initial state of a taxi.
     */
    @Test
    public void testCreation()
    {
        assertEquals(true, taxi.isFree());
    }
    
    /**
     * Test that a taxi is no longer free after it has
     * picked up a passenger.
     */
    @Test
    public void testPickup()
    {
        taxi.pickup(passenger);
        assertEquals(false, taxi.isFree());
    }
    
    /**
     * Test that a taxi becomes free again after offloading
     * a passenger.
     */
    public void testOffload()
    {
        taxi.pickup(passenger);
        assertEquals(false, taxi.isFree());
        taxi.offloadPassenger();
        assertEquals(true, taxi.isFree());
    }
    
    /**
     * Test that a taxi picks up and delivers a passenger within
     * a reasonable number of steps.
     */
    public void testDelivery()
    {
        Location pickupLocation = passenger.getPickupLocation();
        Location destination = passenger.getDestination();
        // The number of steps expected is the sum of the taxi's
        // distance to the passenger and the distance from there
        // to the destination.
        int stepsExpected = taxi.getLocation().distance(pickupLocation) +
                    pickupLocation.distance(destination);
        
        taxi.pickup(passenger);

        int steps = 0;
        while(!taxi.isFree() && steps < stepsExpected) {
            taxi.act();
            steps++;
        }
        assertEquals(steps, stepsExpected);
        assertEquals(taxi.isFree(), true);
    }
}

