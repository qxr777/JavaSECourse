/**
 * A class to model an item (or set of items) in an
 * auction: a lot.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Lot
{
    // A unique identifying number.
    private final int number;
    // A description of the lot.
    private String description;
    // The current highest bid for this lot.
    private Bid highestBid;

    /**
     * Construct a Lot, setting its number and description.
     * @param number The lot number.
     * @param description A description of this lot.
     */
    public Lot(int number, String description)
    {
        this.number = number;
        this.description = description;
        this.highestBid = null;
    }

    /**
     * Attempt to bid for this lot. A successful bid
     * must have a value higher than any existing bid.
     * @param bid A new bid.
     * @return true if successful, false otherwise
     */
    public boolean bidFor(Bid bid)
    {
        if(highestBid == null) {
            // There is no previous bid.
            highestBid = bid;
            return true;
        }
        else if(bid.getValue() > highestBid.getValue()) {
            // The bid is better than the previous one.
            highestBid = bid;
            return true;
        }
        else {
            // The bid is not better.
            return false;
        }
    }
    
    /**
     * @return A string representation of this lot's details.
     */
    public String toString()
    {
        String details = number + ": " + description;
        if(highestBid != null) {
            details += "    Bid: " + 
                       highestBid.getValue();
        }
        else {
            details += "    (No bid)";
        }
        return details;
    }

    /**
     * @return The lot's number.
     */
    public int getNumber()
    {
        return number;
    }

    /**
     * @return The lot's description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return The highest bid for this lot.
     *         This could be null if there is
     *         no current bid.
     */
    public Bid getHighestBid()
    {
        return highestBid;
    }
}
