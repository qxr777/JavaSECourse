import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * An outline test class for AddressBook.
 * Not all methods are tested. Neither are those methods
 * that are tested tested thoroughly.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class AddressBookTest
{
    // Sample contact details.
    private ContactDetails[] sampleDetails;
    // An address book containing sample data.
    private AddressBook book;
    // A copy of one of the contact's details.
    private ContactDetails existingContact;
    // An additional contact.
    private ContactDetails furtherContact;
    // Revised details for an existing contact.
    private ContactDetails revisedDetails;

    /**
     * Set up the sample details.
     */
    public AddressBookTest()
    {
        // A sample of contact details to be stored in the book.
        sampleDetails = new ContactDetails[] {
            new ContactDetails("david",   "08459 100000", "address 1"),
            new ContactDetails("michael", "08459 200000", "address 2"),
            new ContactDetails("john",    "08459 300000", "address 3"),
            new ContactDetails("helen",   "08459 400000", "address 4"),
            new ContactDetails("emma",    "08459 500000", "address 5"),
            new ContactDetails("kate",    "08459 600000", "address 6"),
            new ContactDetails("chris",   "08459 700000", "address 7"),
            new ContactDetails("ruth",    "08459 800000", "address 8"),
        };
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        book = new AddressBook();
        for(ContactDetails details : sampleDetails) {
            book.addDetails(details);
        }
        // Take a copy of the details of one of the contacts.
        ContactDetails first = sampleDetails[0];
        existingContact = new ContactDetails(first.getName(), first.getPhone(),
                                     first.getAddress());
        // Create a further contact who is not yet in the address book.
        furtherContact = new ContactDetails("ian", "08459 900000", "address 9");
        // Change the address of an existing contact.
        revisedDetails = new ContactDetails(existingContact.getName(),
                                            existingContact.getPhone(),
                                            existingContact.getAddress() + "X");

    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        book = null;
        existingContact = null;
        furtherContact = null;
        revisedDetails = null;
    }

    /**
     * Test that the count matches the number of sample entries.
     * Then remove one and check for a reduced count.
     * Then add one and check for an increased count.
     */
    @Test
    public void testGetNumberOfEntries()
    {
        assertEquals(sampleDetails.length, book.getNumberOfEntries());
        book.removeDetails(existingContact.getName());
        assertEquals(sampleDetails.length - 1, book.getNumberOfEntries());
        book.addDetails(furtherContact);
        assertEquals(sampleDetails.length, book.getNumberOfEntries());
    }

    /**
     * Check that the existing contact can be found, and that a non-existent
     * one cannot be found.
     */
    @Test
    public void testGetDetails()
    {
        assertEquals(existingContact, book.getDetails(existingContact.getName()));
        assertNull(book.getDetails(furtherContact.getName()));
    }

    /**
     * Test that the name and phone numbers of an existing contact
     * are registered as existing keys.
     */
    @Test
    public void testKeyInUse()
    {
        assertEquals(true, book.keyInUse(existingContact.getName()));
        assertEquals(true, book.keyInUse(existingContact.getPhone()));
    }

    /**
     * Test that a further contact can be added.
     */
    @Test
    public void testAddDetails()
    {
        assertEquals(false, book.keyInUse("ian"));
        book.addDetails(furtherContact);
        assertEquals(true, book.keyInUse("ian"));
        assertEquals(sampleDetails.length + 1, book.getNumberOfEntries());
    }

    /**
     * Test that we can add a further contact and then remove them.
     */
    @Test
    public void testRemoveDetails()
    {
        book.addDetails(furtherContact);
        assertEquals(true, book.keyInUse("ian"));
        book.removeDetails("ian");
        assertEquals(false, book.keyInUse("ian"));
    }

    /**
     * Test that details of an existing contact can be changed.
     */
    @Test
    public void testChangeDetails()
    {
        assertEquals(existingContact, book.getDetails(existingContact.getName()));
        book.changeDetails(existingContact.getName(), revisedDetails);
        assertEquals(revisedDetails, book.getDetails(revisedDetails.getName()));
    }

    /**
     * Test that a search for an existing contact finds one entry.
     * Then test that a search for a non-existent contact fails
     * to find any entries.
     * Then add a new contact and test that a search finds them.
     */
    @Test
    public void testSearch()
    {
        assertEquals(book.search(existingContact.getName()).length, 1);
        assertEquals(book.search(furtherContact.getName()).length, 0);
        book.addDetails(furtherContact);
        assertEquals(book.search(furtherContact.getName()).length, 1);
    }
    
    /**
     * Trigger an assertion error by adding revised details via
     * addDetails rather than changeDetails.
     */
    @Test
    public void testAddDetailsError()
    {
        assertEquals(existingContact, book.getDetails(existingContact.getName()));
        book.addDetails(revisedDetails);
        assertEquals(existingContact, book.getDetails(existingContact.getName()));
        assertEquals(revisedDetails, book.getDetails(revisedDetails.getName()));
    }
}








