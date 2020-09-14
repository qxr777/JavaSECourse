
/**
 * This class represents a comment left for a sales item on an online sales site.
 * A comment consists of a comment text, a rating, and an author name. Other users
 * can indicate whether the comment was useful or not (called 'upvoting' or 
 * 'downvoting'). The balance between upvotes and downvotes is stored with comments.
 * A negative vote balance means that the comment received more downvotes than upvotes.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Comment
{
    private String author;
    private String text;
    private int rating;
    private int votes;

    /**
     * Create a comment with all necessary details. The initial vote balance is 0.
     */
    public Comment(String author, String text, int rating)
    {
        this.author = author;
        this.text = text;
        this.rating = rating;
        votes = 0;
    }

    /**
     * Indicate that this comment is useful ('upvote'). This is used when a reader clicks
     * the 'Yes' button after the "Was this comment helpful?" quesion.
     */
    public void upvote()
    {
        votes++;
    }

    /**
     * Indicate that this comment is not useful ('downvote'). This is used when a reader
     * clicks the 'No' button after the "Was this comment helpful?" quesion.
     */
    public void downvote()
    {
        votes--;
    }
    
    /**
     * Return the author of this comment.
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * Return the rating of this comment.
     */
    public int getRating()
    {
        return rating;
    }
    
    /**
     * Return the vote count (balance of up and down-votes).
     */
    public int getVoteCount()
    {
        return votes;
    }
    
    /**
     * Return the full text and details of the comment, including 
     * the comment text, author and rating.
     */
    public String getFullDetails()
    {
        String details = "Rating: " + "*****".substring(0,rating) + "    "
                         + "By: " + author + "\n"
                         + "    " + text + "\n";
        // Note: 'votes' is currently included for testing purposes only. In the final
        // application, this will nt be shown. Instead, the vote count will be used to 
        // select and order the comments on screen.
        details += "(Voted as helpful: " + votes + ")\n";
        return details;
    }
}
