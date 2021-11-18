package PropertyManager;

// This class represents a single expense event.

import java.text.DecimalFormat;
import java.util.HashMap;

public class Expense {

    // decimal format for the monetary value
    private static DecimalFormat df = EnvManager.getDecimalFormat();


    // DATA FIELDS
    private int propertyID;       // the ID of the property for which the expense was incurred
    private String description;   // a description of the expense
    private double cost;          // the cost of the expense
    private String date;          // the date on which the expense was incurred
    
    // As this class is not used for processing date, the date is String datatype for the simplicity
    // the date is in yyyy-MM-dd format


    /**
     * Non-Argument Constructor
     */
    public Expense() {
        propertyID = -1;
        description = "";
        cost = -1;
        date = "2000-01-01";
    }

    /**
     * Constructor
     *
     * @param ID - the ID of the property for which the expense was incurred
     * @param descr - the description of the expense
     * @param cost - the cost of the expense
     * @param date - the date on which the expense was incurred
     */
    public Expense(int ID, String descr, double cost, String date) {
        propertyID = ID;
        description = descr;
        this.cost = cost;
        this.date = date;
    }


    /**
     * @return the summary details of the expense event
     */
    public String toString() {
        // the hashmaps to convert foreign IDs to meaningful information
        HashMap<Integer, Client> clientsHashMap = HashMapContainer.getClientsHashMap();
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();

        String toStr = "Property Address:    " + propertiesHashMap.get(propertyID).getAddress()
                + "\nProperty Owner:\t     "
                + clientsHashMap.get(propertiesHashMap.get(propertyID).getClientID()).getFullName()
                + "\nExpense Description: " + description
                + "\nMonetary Amount:     $ " + df.format(cost)
                + "\nCurrent Date:\t     " + date;
        return toStr;
    }
    

    /**
     * @return the ID of the property for which the expense was incurred
     */
    public int getPropertyID() {
        return propertyID;
    }

    /**
     * @return the description of the expense
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the cost of the expense
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return the date on which the expense was incurred
     */
    public String getDate() {
        return date;
    }


    /**
     * @param ID - the ID of the property for which the expense was incurred
     */
    public void setPropertyID(int ID) {
        propertyID = ID;
    }

    /**
     * @param descr - the description of the expense
     */
    public void setDescription(String descr) {
        description = descr;
    }

    /**
     * @param cost - the cost of the expense
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @param date - the date on which the expense was incurred
     */
    public void setDate(String date) {
        this.date = date;
    }
}
