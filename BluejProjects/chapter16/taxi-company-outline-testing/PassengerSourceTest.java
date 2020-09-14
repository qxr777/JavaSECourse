import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PassengerSourceTest.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class PassengerSourceTest
{
    private PassengerSource source;
    
    /**
     * Default constructor for test class PassengerSourceTest
     */
    public PassengerSourceTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        TaxiCompany company = new TaxiCompany();
        source = new PassengerSource(company);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        source = null;
    }

    /**
     * Test for successful pickup of a passenger.
     */
    @Test
    public void testPickup()
    {
        assertEquals(true, source.requestPickup());
    }
}

