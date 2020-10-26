package cn.edu.whut.cs.java.dome;

/**
 * The DVD class represents a DVD object. Information about the 
 * DVD is stored and can be retrieved. We assume that we only deal 
 * with movie DVDs at this stage.
 * 
 */
public class DVD 
{
    private String title;
    private String director;
    private int playingTime; // playing time of the main feature
    private boolean gotIt;
    private String comment;

    /**
     * Constructor for objects of class DVD
     * @param theTitle The title of this DVD.
     * @param theDirector The director of this DVD.
     * @param time The running time of the main feature.
     */
    public DVD(String theTitle, String theDirector, int time)
    {
        title = theTitle;
        director = theDirector;
        playingTime = time;
        gotIt = false;
        comment = "<no comment>";
    }

    /**
     * Enter a comment for this DVD.
     * @param comment The comment to be entered.
     */
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    /**
     * @return The comment for this DVD.
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * Set the flag indicating whether we own this DVD.
     * @param ownIt true if we own the DVD, false otherwise.
     */
    public void setOwn(boolean ownIt)
    {
        gotIt = ownIt;
    }

    /**
     * @return true if we own a copy of this DVD.
     */
    public boolean getOwn()
    {
        return gotIt;
    }

    /**
     * Print details about this DVD to the text terminal.
     */
	public void print() {
		System.out.print("DVD: " + title + " (" + playingTime + " mins)");
		if (gotIt) {
			System.out.println("*");
		} else {
			System.out.println();
		}
		System.out.println("    " + director);
		System.out.println("    " + comment);
	}
}
