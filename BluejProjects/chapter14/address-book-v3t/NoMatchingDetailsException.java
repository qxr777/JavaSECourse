/**
 * Capture a key that failed to match an entry
 * in the address book.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class NoMatchingDetailsException extends Exception
{
    // The key with no match.
    private String key;

    /**
     * Store the details in error.
     * @param key The key with no match.
     */
    public NoMatchingDetailsException(String key)
    {
        this.key = key;
    }

    /**
     * @return The key in error.
     */
    public String getKey()
    {
        return key;
    }
    
    /**
     * @return A diagnostic string containing the key in error.
     */
    public String toString()
    {
        return "No details matching: " + key + " were found.";
    }
}
