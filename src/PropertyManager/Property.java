package PropertyManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

// This class represents an individual property.

public class Property {

    // decimal format for the monetary value
    private static DecimalFormat df = EnvManager.getDecimalFormat();

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
     * Constructor
     *
     * @param ID - the unique numeric identifier for a property
     */
    public Property(int ID) {
        this.ID = ID;
        address = new Address("N/A", "N/A", "N/A", "0000");
        rentWeekly = -1;
        managementRate = -1;
        clientID = -1;
    }


    /**
     * Constructor
     *
     * @param ID - the unique numeric identifier for a property
     * @param clientID - the client ID of the property's owner
     */
    public Property(int ID, int clientID) {
        this.ID = ID;
        address = new Address("N/A", "N/A", "N/A", "0000");
        rentWeekly = -1;
        managementRate = -1;
        this.clientID = clientID;
    }


    /**
     * @return the summary details of the property
     */
    public String toString() {
        // the hashmap of clients to convert clientID to their name
        HashMap<Integer, Client> clientsHashMap = HashMapContainer.getClientsHashMap();

        String toStr = "ID:\t " +  ID
                + "\nAddress: " + address
                + "\nOwner:\t " + clientsHashMap.get(clientID).getFullName()
                + "\nRent:\t $ " + df.format(rentWeekly) + " /week";
        return toStr;
    }


    /**
     * Evaluate the total amount of rent collected in this property.
     *
     * @param rents - the arraylist of all rents, must be sorted by propertyID in ascending order
     * @return the total amount of rent collected
     */
    public double getTotalRent(ArrayList<Rent> rents) {

        double sumRents = 0;

        for (int i = 0; i < rents.size(); i++) {
            if (rents.get(i).getPropertyID() == ID)
                sumRents += rents.get(i).getRentAmount();
            else if (rents.get(i).getPropertyID() > ID)
                break;
        }

        return sumRents;
    }

    /**
     * Evaluate the total amount of cost for all expenses in this property.
     *
     * @param expenses - the arraylist of all expenses, must be sorted by propertyID in ascending order
     * @return the total amount of cost for all expenses
     */
    public double getTotalExpenses(ArrayList<Expense> expenses) {

        double sumCosts = 0;

        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getPropertyID() == ID)
                sumCosts += expenses.get(i).getCost();
            else if (expenses.get(i).getPropertyID() > ID)
                break;
        }

        return sumCosts;
    }

    /**
     * Evaluate the total management fee in this property.
     *
     * @param rents - the arraylist of all rents, must be sorted by propertyID in ascending order
     * @return the total amount of cost for management fee
     */
    public double getManagementFee(ArrayList<Rent> rents) {
        return (getTotalRent(rents) * managementRate);
    }

    /**
     * Evaluate the net monetary amount of this property.
     *
     * @param rents - the arraylist of all rents, must be sorted by propertyID in ascending order
     * @param expenses - the arraylist of all expenses, must be sorted by propertyID in ascending order
     * @return the net monetary amount of this property
     */
    public double getNetBalance(ArrayList<Rent> rents, ArrayList<Expense> expenses) {
        return (getTotalRent(rents) - getManagementFee(rents) - getTotalExpenses(expenses));
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
