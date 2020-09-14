/**
 * Provide a simple demonstration of the AddressBook class.
 * Sample data is added to the address book.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class AddressBookDemo
{
    private AddressBook book;

    /**
     * Setup an AddressBook with sample data.
     * The address book can be retrieved using the getBook method. 
     */
    public AddressBookDemo()
    {
        ContactDetails[] sampleDetails = {
            new ContactDetails("david",   "08459 100000", "address 1"),
            new ContactDetails("michael", "08459 200000", "address 2"),
            new ContactDetails("john",    "08459 300000", "address 3"),
            new ContactDetails("helen",   "08459 400000", "address 4"),
            new ContactDetails("emma",    "08459 500000", "address 5"),
            new ContactDetails("kate",    "08459 600000", "address 6"),
            new ContactDetails("chris",   "08459 700000", "address 7"),
            new ContactDetails("ruth",    "08459 800000", "address 8"),
        };
        book = new AddressBook();
        for(ContactDetails details : sampleDetails) {
            book.addDetails(details);
        }
    }

    /**
     * @return The sample address book.
     */
    public AddressBook getBook()
    {
        return book;
    }
}
