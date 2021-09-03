package cn.edu.whut.cs.java.dome;

/**
 * The DVD class represents a DVD object. Information about the 
 * DVD is stored and can be retrieved. We assume that we only deal 
 * with movie DVDs at this stage.
 * 
 */
public class DVD extends Item
{
    private String director;

    /**
     * Constructor for objects of class DVD
     * @param theTitle The title of this DVD.
     * @param theDirector The director of this DVD.
     * @param time The running time of the main feature.
     */
    public DVD(String theTitle, String theDirector, int time)
    {
        super(theTitle, time);
        director = theDirector;
    }

    /**
     * Print details about this CD to the text terminal.
     */
    public void print() {
        System.out.print("DVD : " + title + " (" + playingTime + " mins)");
        if (gotIt) {
            System.out.println("*");
        } else {
            System.out.println();
        }
        System.out.println("    " + director);
        System.out.println("    " + comment);
    }

}
