import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a mail server. The server is able to receive
 * mail items for storage, and deliver them to clients on demand.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class MailServer
{
    // Storage for the arbitrary number of mail items to be stored
    // on the server.
    private List<MailItem> items;

    /**
     * Construct a mail server.
     */
    public MailServer()
    {
        items = new ArrayList<>();
    }

    /**
     * Return how many mail items are waiting for a user.
     * @param who The user to check for.
     * @return How many items are waiting.
     */
    public int howManyMailItems(String who)
    {
        int count = 0;
        for(MailItem item : items) {
            if(item.getTo().equals(who)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Return the next mail item for a user or null if there
     * are none.
     * @param who The user requesting their next item.
     * @return The user's next item.
     */
    public MailItem getNextMailItem(String who)
    {
        Iterator<MailItem> it = items.iterator();
        while(it.hasNext()) {
            MailItem item = it.next();
            if(item.getTo().equals(who)) {
                it.remove();
                return item;
            }
        }
        return null;
    }

    /**
     * Add the given mail item to the message list.
     * @param item The mail item to be stored on the server.
     */
    public void post(MailItem item)
    {
        items.add(item);
    }
}
