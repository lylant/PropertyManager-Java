package PropertyManager;

public class Property {

    // DATA FIELDS
    private int ID;                   // a unique numeric identifier for a property
    private Address address;          // the property address
    private double rentWeekly;        // the rental amount charged for the property per week in dollars
    private double managementRate;    // the percentage of the weekly rent claimed for the management
    private int clientID;             // the client ID of the property's owner

    // rentWeekly(the weekly charge) is declared as double datatype for the further flexibility


    /**
     * Non-Argument Constructor
     */
    public Property() {
        ID = -1;
        address = new Address("N/A", "N/A", "N/A", "0000");
        rentWeekly = -1;
        managementRate = -1;
        clientID = -1;
    }


    /**
     * @return the unique numeric identifier for a property
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the street of the property address
     */
    public String getStreet() {
        return address.getStreet();
    }

    /**
     * @return the suburb of the property address
     */
    public String getSuburb() {
        return address.getSuburb();
    }

    /**
     * @return the state of the property address
     */
    public String getState() {
        return address.getState();
    }

    /**
     * @return the postcode of the property address
     */
    public String getPostcode() {
        return address.getPostcode();
    }

    /**
     * @return the address of the property
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the rental amount charged for the property per week in dollars
     */
    public double getRentWeekly() {
        return rentWeekly;
    }

    /**
     * @return the percentage of the weekly rent claimed as a fee for managing this property
     */
    public double getManagementRate() {
        return managementRate;
    }

    /**
     * @return the client ID of the property's owner
     */
    public int getClientID() {
        return clientID;
    }


    /**
     * @param ID - the unique numeric identifier for a property
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @param street - the street of the property address
     */
    public void setStreet(String street) {
        address.setStreet(street);
    }

    /**
     * @param suburb - the suburb of the property address
     */
    public void setSuburb(String suburb) {
        address.setSuburb(suburb);
    }

    /**
     * @param state - the state of the property address
     */
    public void setState(String state) {
        address.setState(state);
    }

    /**
     * @param postcode - the postcode of the property address
     */
    public void setPostcode(String postcode) {
        address.setPostcode(postcode);
    }

    /**
     * @param wRent - the rental amount charged for the property per week in dollars
     */
    public void setRentWeekly(double wRent) {
        rentWeekly = wRent;
    }

    /**
     * @param mRate - the percentage of the weekly rent claimed as a fee for managing this property
     */
    public void setManagementRate(double mRate) {
        managementRate = mRate;
    }

    /**
     * @param cID - the client ID of the property's owner
     */
    public void setClientID(int cID) {
        clientID = cID;
    }
}
