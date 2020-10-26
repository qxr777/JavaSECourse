package cn.edu.whut.cs.java.dome;
/**
 * The CD class represents a CD object. Information about the 
 * CD is stored and can be retrieved.
 * 
 */
public class CD extends Item
{
    private String artist;
    private int numberOfTracks;

    /**
     * Initialize the CD.
     * @param theTitle The title of the CD.
     * @param theArtist The artist of the CD.
     * @param tracks The number of tracks on the CD.
     * @param time The playing time of the CD.
     */
    public CD(String theTitle, String theArtist, int tracks, int time)
    {
        super(theTitle, time);
    	artist = theArtist;
        numberOfTracks = tracks;
    }

}
