/**
 * Set up a passenger-source and taxi-company, to illustrate
 * the scenario of requesting a pickup.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Demo
{
    private PassengerSource source;
    private TaxiCompany company;

    /**
     * Create some sample objects for the demo.
     */
    public Demo()
    {
        company = new TaxiCompany();
        source = new PassengerSource(company);
    }

    /**
     * Request a pickup for a new passenger.
     * The request will fail in this version.
     */
    public void pickupTest()
    {
        if(source.requestPickup()) {
            System.out.println("Pickup request succeeded.");
        }
        else {
            System.out.println("Pickup request failed.");
        }
    }
}
