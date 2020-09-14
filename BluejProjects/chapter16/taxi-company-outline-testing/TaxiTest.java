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
        Location taxiLocation = new Location();
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
        Location pickup = new Location();
        Location destination = new Location();
        Passenger passenger = new Passenger(pickup, destination);
        taxi.pickup(passenger);
        assertEquals(false, taxi.isFree());
    }
    
    /**
     * Test that a taxi becomes free again after offloading
     * a passenger.
     */
    @Test
    public void testOffload()
    {
        testPickup();
        taxi.offloadPassenger();
        assertEquals(true, taxi.isFree());
    }
}

