package cn.edu.whut.cs.java.dome;

public class Item {
    protected String title;
    protected int playingTime;
    protected boolean gotIt;
    protected String comment;

    public Item(String theTitle, int time)
    {
        title = theTitle;
        playingTime = time;
        gotIt = false;
        comment = "<no comment>";
    }

    /**
     * Enter a comment for this CD.
     * @param comment The comment to be entered.
     */
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    /**
     * @return The comment for this CD.
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * Set the flag indicating whether we own this CD.
     * @param ownIt true if we own the CD, false otherwise.
     */
    public void setOwn(boolean ownIt)
    {
        gotIt = ownIt;
    }

    /**
     * @return true if we own a copy of this CD.
     */
    public boolean getOwn()
    {
        return gotIt;
    }

    public void print() {
    }

//    /**
//     * Print details about this CD to the text terminal.
//     */
//	public void print() {
//		System.out.print("ITEM : " + title + " (" + playingTime + " mins)");
//		if (gotIt) {
//			System.out.println("*");
//		} else {
//			System.out.println();
//		}
//		System.out.println("    " + comment);
//	}
}
