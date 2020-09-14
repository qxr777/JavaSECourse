import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A helper class for our music application. This class can read files from the file system
 * from a given folder with a specified suffix. It will interpret the file name as artist/
 * track title information.
 * 
 * It is expected that file names of music tracks follow a standard format of artist name
 * and track name, separated by a dash. For example: TheBeatles-HereComesTheSun.mp3
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TrackReader
{
    /**
     * Create the track reader, ready to read tracks from the music library folder.
     */
    public TrackReader()
    {
        // Nothing to do here.
    }
    
    /**
     * Read music files from the given library folder
     * with the given suffix.
     * @param folder The folder to look for files.
     * @param suffix The suffix of the audio type.
     */
    public ArrayList<Track> readTracks(String folder, final String suffix)
    {
        File audioFolder = new File(folder);
        ArrayList<Track> tracks = new ArrayList<>();
        File[] audioFiles = audioFolder.listFiles(new FilenameFilter() {
            /**
             * Accept files with matching suffix.
             * @param dir The directory containing the file.
             * @param name The name of the file.
             * @return true if the name ends with the suffix.
             */
            public boolean accept(File dir, String name)
            {
                return name.toLowerCase().endsWith(suffix);
            }
        });
        
        // Put all the matching files into the organizer.
        for(File file : audioFiles) {
            Track trackDetails = decodeDetails(file);
            tracks.add(trackDetails);
        }
        return tracks;
    }

    /**
     * Try to decode details of the artist and the title
     * from the file name.
     * It is assumed that the details are in the form:
     *     artist-title.mp3
     * @param file The track file.
     * @return A Track containing the details.
     */
    private Track decodeDetails(File file)
    {
        // The information needed.
        String artist = "unknown";
        String title = "unknown";
        String filename = file.getPath();
        
        // Look for artist and title in the name of the file.
        String details = file.getName();
        String[] parts = details.split("-");
        
        if(parts.length == 2) {
            artist = parts[0];
            String titlePart = parts[1];
            // Remove a file-type suffix.
            parts = titlePart.split("\\.");
            if(parts.length >= 1) {
                title = parts[0];
            }
            else {
                title = titlePart;
            }
        }
        return new Track(artist, title, filename);
    }
}
