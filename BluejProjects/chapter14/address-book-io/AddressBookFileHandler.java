import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

/**
 * Provide a range of file-handling operations on an AddressBook.
 * These methods demonstrate a range of basic features of the
 * java.io package.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class AddressBookFileHandler
{
    // The address book on which i/o operations are performed.
    private AddressBook book;
    // The name of a file used to store search results.
    private static final String RESULTS_FILE = "results.txt";

    /**
     * Constructor for objects of class FileHandler.
     * @param book The address book to use.
     */
    public AddressBookFileHandler(AddressBook book)
    {
        this.book = book;
    }
    
    /**
     * Save the results of an address-book search to
     * the file "results.txt" in the project folder.
     * @param keyPrefix The key prefix to search on.
     */
    public void saveSearchResults(String keyPrefix) throws IOException
    {
        Path resultsFile = Paths.get(RESULTS_FILE).toAbsolutePath();
        ContactDetails[] results = book.search(keyPrefix);
        FileWriter writer = new FileWriter(resultsFile.toString());
        for(ContactDetails details : results) {
            writer.write(details.toString());
            writer.write('\n');
            writer.write('\n');
        }
        writer.close();
    }
    
    /**
     * Show the results from the most-recent call to
     * saveSearchResults. As output is to the console, any
     * problems are reported directly by this method.
     */
    public void showSearchResults()
    {
        BufferedReader reader = null;
        try {
            Path resultsFile = Paths.get(RESULTS_FILE).toAbsolutePath();
            reader = new BufferedReader(new FileReader(resultsFile.toString()));
            System.out.println("Results ...");
            String line;
            line = reader.readLine();
            while(line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            System.out.println();
        }
        catch(FileNotFoundException e) {
            System.out.println("Unable to find the file: " +
                               RESULTS_FILE);
        }
        catch(IOException e) {
            System.out.println("Error encountered reading the file: " +
                               RESULTS_FILE);
        }
        finally {
            if(reader != null) {
                // Catch any exception, but nothing can be done
                // about it.
                try {
                    reader.close();
                }
                catch(IOException e) {
                    System.out.println("Error on closing: " +
                                       RESULTS_FILE);
                }
            }
        }
    }

    /**
     * Add further entries to the address book, from a text file.
     * The file is assumed to contain one element per line,
     * plus a blank line, for each entry:
     *     name \n phone \n address \n \n
     * A line may be blank if that part of the details is missing.
     * @param filename The text file containing the details.
     * @throws IOException On input failure.
     */
    public void addEntriesFromFile(String filename) throws IOException
    {
        // Make sure the file can be found.
        URL resource = getClass().getResource(filename);
        if(resource == null) {
            throw new FileNotFoundException(filename);
        }
        filename = resource.getFile();
        BufferedReader reader = new BufferedReader(
                                   new FileReader(filename));
        String name;
        name = reader.readLine();
        while(name != null) {
            String phone = reader.readLine();
            String address = reader.readLine();
            // Discard the separating blank line.
            reader.readLine();
            book.addDetails(new ContactDetails(name, phone,
                                               address));
            name = reader.readLine();
        }
        reader.close();
    }
    
    /**
     * Read the binary version of an address book from the given file.
     * If the file name is not an absolute path, then it is assumed
     * to be relative to the current project folder.
     * @param sourceFile The file from where the details are to be read.
     * @return The address book object.
     * @throws IOException If the reading process fails for any reason.
     */
    public AddressBook readFromFile(String sourceFile)
        throws IOException, ClassNotFoundException
    {
        // Make sure the file can be found.
        URL resource = getClass().getResource(sourceFile);
        if(resource == null) {
            throw new FileNotFoundException(sourceFile);
        }
        try {
            File source = new File(resource.toURI());
            ObjectInputStream is = new ObjectInputStream(
                                    new FileInputStream(source));
            AddressBook savedBook = (AddressBook) is.readObject();
            is.close();
            return savedBook;
        }
        catch(URISyntaxException e) {
            throw new IOException("Unable to make a valid filename for " +
                                  sourceFile);
        }
    }
    
    /**
     * Save a binary version of the address book to the given file.
     * If the file name is not an absolute path, then it is assumed
     * to be relative to the current project folder.
     * @param destinationFile The file where the details are to be saved.
     * @throws IOException If the saving process fails for any reason.
     */
    public void saveToFile(String destinationFile) throws IOException
    {
        Path destination = Paths.get(destinationFile).toAbsolutePath();
        ObjectOutputStream os = new ObjectOutputStream(
                                    new FileOutputStream(destination.toString()));
        os.writeObject(book);
        os.close();
    }
}
