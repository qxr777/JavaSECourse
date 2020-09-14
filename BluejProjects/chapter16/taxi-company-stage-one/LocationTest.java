import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test implementation of the Location class.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class LocationTest
{
    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
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
     * Test the distance method of the Location class.
     */
    @Test
    public void testDistance()
    {
        boolean ok = true;
        int startX = 10, startY = 10;
        Location startLocation = new Location(startX, startY);
        
        // Calculate the distance from startLocation to other
        // locations around it. The distance should always
        // be equal to offset.
        int offset = 5;
        assertEquals(startLocation.distance(
            new Location(startX, startY + offset)), offset);
        assertEquals(startLocation.distance(
            new Location(startX + offset, startY)), offset);
        assertEquals(startLocation.distance(
            new Location(startX + 1, startY + offset)), offset);
        assertEquals(startLocation.distance(
            new Location(startX + offset, startY + 1)), offset);
        assertEquals(startLocation.distance(
            new Location(startX + offset, startY + offset)), offset);
        assertEquals(startLocation.distance(
            new Location(startX + offset - 1, startY + offset)), offset);
        assertEquals(startLocation.distance(
            new Location(startX + offset, startY + offset - 1)), offset);
    }
    
    /**
     * Run tests of the nextLocation method of the Location class.
     */
    @Test
    public void testAdjacentLocations()
    {
        int startX = 10, startY = 10;
        Location startLocation = new Location(startX, startY);
        
        // Test immediate adjacency.
        // (x, y) offsets for each direction from (startX, startY).
        int[][] offsets = {
            { 0, 1, 0, 1, -1, 0, -1, 1, -1},
            { 0, 0, 1, 1, 0, -1, -1, -1, 1},
        };

        for(int i = 0; i < offsets[0].length; i++) {
            Location destination = new Location(startX + offsets[0][i],
                                       startY + offsets[1][i]);
            Location nextLocation = startLocation.nextLocation(destination);
            assertEquals(nextLocation.equals(destination), true);
        }
    }
    
    @Test
    public void testNonAdjacentLocations()
    {
        int startX = 10, startY = 10;
        Location startLocation = new Location(startX, startY);
        // (x, y) offsets for each direction from (startX, startY).
        int[][] offsets = {
            { 0, 1, 0, 1, -1, 0, -1, 1, -1},
            { 0, 0, 1, 1, 0, -1, -1, -1, 1},
        };
        // Test with destination that are not adjacent.
        // Use different values for xDist and yDist for more
        // varied tests.
        int xDist = 7;
        int yDist = 3;
        for(int i = 0; i < offsets[0].length; i++) {
            Location destination = new Location(startX + xDist * offsets[0][i],
                                       startY + yDist * offsets[1][i]);
            Location expectedNextLocation =
                        new Location(startX + offsets[0][i],
                                     startY + offsets[1][i]);
            Location nextLocation = startLocation.nextLocation(destination);            
            assertEquals(expectedNextLocation.equals(nextLocation), true);
        }
    }
}
