package PropertyManager;

public class Client {

    // DATA FIELDS
    private int ID;           // a unique numeric identifier for a client
    private String firstName; // the client's given name
    private String lastName;  // the client's surname
    private String street;    // the street of the client's postal address
    private String suburb;    // the suburb of the address
    private String state;     // the state of the address
    private String postcode;  // the postcode of the address

    // the postcode should be String datatype to handle the postcode starting with 0


    /**
     * Non-Argument Constructor
     */
    public Client() {
        ID = -1;
        firstName = "N/A";
        lastName = "N/A";
        street = "N/A";
        suburb = "N/A";
        state = "N/A";
        postcode = "0000";
    }


    /**
     * @return the unique numeric identifier for a client
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the client's given name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the client's surname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the client's full name
     */
    public String getFullName() {
        return (firstName + " " + lastName);
    }

    /**
     * @return the street of the client's postal address
     */
    public String getStreet() {
        return street;
    }

    /**
     * @return the suburb of the client's postal address
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * @return the state of the client's postal address
     */
    public String getState() {
        return state;
    }

    /**
     * @return the postcode of the client's postal address
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @return the postal address of the client
     */
    public String getAddress() {
        return (street + ", " + suburb + ", " + state + " " + postcode);
    }


    /**
     * @param ID - the unique numeric identifier for a client
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @param fName - the client's given name
     */
    public void setFirstName(String fName) {
        firstName = fName;
    }

    /**
     * @param lName - the client's surname
     */
    public void setLastName(String lName) {
        lastName = lName;
    }

    /**
     * Set both data fields for the name, the firstName and the lastName, with given a parameter
     *
     * @param fullName - the client's name in the format: "firstName lastName"
     */
    public void setFullName(String fullName) {
        String[] splitName = fullName.split("\\s+"); // split the fullName String by whitespace
        firstName = splitName[0];
        lastName = splitName[1];
    }

    /**
     * @param street - the street of the client's postal address
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @param suburb - the suburb of the client's postal address
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    /**
     * @param state - the state of the client's postal address
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @param postcode - the postcode of the client's postal address
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
