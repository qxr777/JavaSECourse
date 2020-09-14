/**
 * Details of a sighting of a type of animal by an individual spotter.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Sighting
{
    // The animal spotted.
    private final String animal;
    // The ID of the spotter.
    private final int spotter;
    // How many were seen.
    private final int count;
    // The ID of the area in which they were seen.
    private final int area;
    // The reporting period.
    private final int period;
    
    /**
     * Create a record of a sighting of a particular type of animal.
     * @param animal The animal spotted.
     * @param spotter The ID of the spotter.
     * @param count How many were seen (>= 0).
     * @param area The ID of the area in which they were seen.
     * @param period The reporting period.
     */
    public Sighting(String animal, int spotter, int count, int area, int period)
    {
        this.animal = animal;
        this.spotter = spotter;
        this.count = count;
        this.area = area;
        this.period = period;
    }

    /**
     * Return the type of animal spotted.
     * @return The animal type.
     */
    public String getAnimal() 
    {
        return animal;
    }

    /**
     * Return the ID of the spotter.
     * @return The spotter's ID.
     */
    public int getSpotter() 
    {
        return spotter;
    }

    /**
     * Return how many were spotted.
     * @return The number seen.
     */
    public int getCount() 
    {
        return count;
    }

    /**
     * Return the ID of the area in which they were seen.
     * @return Where they were seen.
     */
    public int getArea() 
    {
        return area;
    }

    /**
     * Return the period in which they were seen.
     * @return When they were seen.
     */
    public int getPeriod() 
    {
        return period;
    }

    /**
     * Return a string containing details of the animal, the number seen,
     * where they were seen, who spotted them and when.
     * @return A string giving details of the sighting.
     */
    public String getDetails() 
    {
        return animal + 
               ", count = " + count + 
               ", area = " + area + 
               ", spotter = " + spotter + 
               ", period = " + period;
    }
    
}
