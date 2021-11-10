package PropertyManager;

// This class represents a single address for Client or Property.
// This class will be aggregated in Client and Property.
public class Address {

    // DATA FIELDS
    private String street;    // the street of the address
    private String suburb;    // the suburb of the address
    private String state;     // the state of the address
    private String postcode;  // the postcode of the address

    // the postcode should be String datatype to handle the postcode starting with 0


    /**
     * Constructor
     *
     * @param street - the street of the address
     * @param suburb - the suburb of the address
     * @param state - the state of the address
     * @param postcode - the postcode of the address
     */
    public Address(String street, String suburb, String state, String postcode) {
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
    }

    /**
     * Copy Constructor
     *
     * @param addr - the address to be copied
     */
    public Address(Address addr) {
        street = addr.street;
        suburb = addr.suburb;
        state = addr.state;
        postcode = addr.postcode;
    }

    /**
     * @return the full address
     */
    public String toString() {
        return (street + ", " + suburb + ", " + state + " " + postcode);
    }


    /**
     * @return the street of the address
     */
    public String getStreet() {
        return street;
    }

    /**
     * @return the suburb of the address
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * @return the state of the address
     */
    public String getState() {
        return state;
    }

    /**
     * @return the postcode of the address
     */
    public String getPostcode() {
        return postcode;
    }


    /**
     * @param street - the street of the address
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @param suburb - the suburb of the address
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    /**
     * @param state - the state of the address
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @param postcode - the postcode of the address
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
