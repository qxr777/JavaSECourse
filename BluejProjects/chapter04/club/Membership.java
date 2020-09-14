/**
 * Store details of a club membership.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Membership
{
    // The name of the member.
    private String name;
    // The month in which the membership was taken out.
    private int month;
    // The year in which the membership was taken out.
    private int year;

    /**
     * Constructor for objects of class Membership.
     * @param name The name of the member.
     * @param month The month in which they joined. (1 ... 12)
     * @param year The year in which they joined.
     */
    public Membership(String name, int month, int year)
        throws IllegalArgumentException
    {
        if(month < 1 || month > 12) {
            throw new IllegalArgumentException(
                "Month " + month + " out of range. Must be in the range 1 ... 12");
        }
        this.name = name;
        this.month = month;
        this.year = year;
    }
    
    /**
     * @return The member's name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return The month in which the member joined.
     *         A value in the range 1 ... 12
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * @return The year in which the member joined.
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @return A string representation of this membership.
     */
    public String toString()
    {
        return "Name: " + name +
               " joined in month " +
               month + " of " + year;
    }
}
