/**
 * Name, address and telephone number details.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class ContactDetails implements Comparable<ContactDetails>
{
    private String name;
    private String phone;
    private String address;

    /**
     * Set up the contact details. All details are trimmed to remove
     * trailing white space.
     * @param name The name.
     * @param phone The phone number.
     * @param address The address.
     */
    public ContactDetails(String name, String phone, String address)
    {
        // Use blank strings if any of the arguments is null.
        if(name == null) {
            name = "";
        }
        if(phone == null) {
            phone = "";
        }
        if(address == null) {
            address = "";
        }
        this.name = name.trim();
        this.phone = phone.trim();
        this.address = address.trim();
    }
    
    /**
     * @return The name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The telephone number.
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @return The address.
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * Test for content equality between two objects.
     * @param other The object to compare to this one.
     * @return true if the argument object is a set
     *              of contact details with matching attributes.
     */
    public boolean equals(Object other)
    {
        if(other instanceof ContactDetails) {
            ContactDetails otherDetails = (ContactDetails) other;
            return name.equals(otherDetails.getName()) &&
                    phone.equals(otherDetails.getPhone()) &&
                     address.equals(otherDetails.getAddress());
        }
        else {
            return false;
        }
    }

    /**
     * Compare these details against another set, for the purpose
     * of sorting. The fields are sorted by name, phone, and address.
     * @param otherDetails The details to be compared against.
     * @return a negative integer if this comes before the parameter,
     *         zero if they are equal and a positive integer if this
     *         comes after the second.
     */
    public int compareTo(ContactDetails otherDetails)
    {
        int comparison = name.compareTo(otherDetails.getName());
        if(comparison != 0){
            return comparison;
        }
        comparison = phone.compareTo(otherDetails.getPhone());
        if(comparison != 0){
            return comparison;
        }
        return address.compareTo(otherDetails.getAddress());
    }

    /**
     * @return A multi-line string containing the name, phone, and address.
     */
    public String toString()
    {
        return name + "\n" + phone + "\n" + address;
    }

    /**
     * Compute a hashcode using the rules to be found in
     * "Effective Java", by Joshua Bloch.
     * @return A hashcode for ContactDetails.
     */
    public int hashCode()
    {
        int code = 17;
        code = 37 * code + name.hashCode();
        code = 37 * code + phone.hashCode();
        code = 37 * code + address.hashCode();
        return code;
    }
}
