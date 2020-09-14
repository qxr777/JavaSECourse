/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * Use the FIELDS class variable for the names of
 * the available attributes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    
    // Names for the available fields.
    public static final String[] FIELDS = {
        "Artist",
        "Title",
        "Filename",
    };
    
    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename)
    {
        setDetails(artist, title, filename);
    }
    
    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
    }
    
    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }
    
    /**
     * Return the value of the named field.
     * The field should be an element of Track.FIELDS
     * @param field Which field to return.
     */
    public String getField(String field) 
    {
        if (field.equals("Artist")) {
            return artist;
        }
        else if (field.equals("Title")) {
            return title;
        }
        else if (field.equals("Filename")) {
            return filename;
        }
        else {
            throw new IllegalArgumentException("Unknown field name: " + field);
        }
    }
    
    /**
     * Return the values of the fields.
     * @return The fields.
     */
    public String[] getFields()
    {
        String[] fields = new String[FIELDS.length];
        for(int i = 0; i < FIELDS.length; i++) {
            fields[i] = getField(FIELDS[i]);
        }
        return fields;
    }
            
        
    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + ")";
    }
    
    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
    }
    
}
