/**
 * Simulate passengers requesting rides from a taxi company.
 * Passengers should be generated at random intervals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class PassengerSource
{
    private TaxiCompany company;

    /**
     * Constructor for objects of class PassengerSource.
     * @param company The company to be used. Must not be null.
     * @throws NullPointerException if company is null.
     */
    public PassengerSource(TaxiCompany company)
    {
        if(company == null) {
            throw new NullPointerException("company");
        }
        this.company = company;
    }

    /**
     * Have the source generate a new passenger and
     * request a pickup from the company.
     * @return true If the request succeeds, false otherwise.
     */
    public boolean requestPickup()
    {
        Passenger passenger = createPassenger();
        return company.requestPickup(passenger);
    }

    /**
     * Create a new passenger.
     * @return The created passenger.
     */
    public Passenger createPassenger()
    {
        return new Passenger(new Location(0, 0), new Location(10, 20));
    }
}
