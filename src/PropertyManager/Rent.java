package PropertyManager;

import java.text.DecimalFormat;
import java.util.HashMap;

// This class represents a single rent collection event.

public class Rent {

    // decimal format for the monetary value
    private static DecimalFormat df = EnvManager.getDecimalFormat();
    
    // DATA FIELDS
    private int propertyID;    // the ID of the property for which the rent was collected
    private double rentAmount; // the monetary amount of rent collected
    private String date;       // the date on which the rent was collected

    // As this class is not used for processing date, the date is String datatype for the simplicity
    // the date is in yyyy-MM-dd format


    /**
     * Non-Argument Constructor
     */
    public Rent() {
        propertyID = -1;
        rentAmount = -1;
        date = "2000-01-01";
    }

    /**
     * Constructor
     *
     * @param ID - the ID of the property for which the rent was collected
     * @param rent - the monetary amount of rent collected
     * @param date - the date on which the rent was collected
     */
    public Rent(int ID, double rent, String date) {
        propertyID = ID;
        rentAmount = rent;
        this.date = date;
    }


    /**
     * @return the summary details of the rent collection event
     */
    public String toString() {
        // the hashmaps to convert foreign IDs to meaningful information
        HashMap<Integer, Client> clientsHashMap = HashMapContainer.getClientsHashMap();
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();

        String toStr = "Property Address:  " + propertiesHashMap.get(propertyID).getAddress()
                + "\nProperty Owner:\t   "
                + clientsHashMap.get(propertiesHashMap.get(propertyID).getClientID()).getFullName()
                + "\nNumber of Weeks:   "
                + (int)(rentAmount/propertiesHashMap.get(propertyID).getRentWeekly())
                + "\nMonetary Amount:   $ " + df.format(rentAmount)
                + "\nCurrent Date:\t   " + date;
        return toStr;
    }


    /**
     * @return the ID of the property for which the rent was collected
     */
    public int getPropertyID() {
        return propertyID;
    }

    /**
     * @return the monetary amount of rent collected
     */
    public double getRentAmount() {
        return rentAmount;
    }

    /**
     * @return the date on which the rent was collected
     */
    public String getDate() {
        return date;
    }


    /**
     * @param ID - the ID of the property for which the rent was collected
     */
    public void setPropertyID(int ID) {
        propertyID = ID;
    }

    /**
     * @param rent - the monetary amount of rent collected
     */
    public void setRentAmount(double rent) {
        rentAmount = rent;
    }

    /**
     * @param date - the date on which the rent was collected
     */
    public void setDate(String date) {
        this.date = date;
    }
}
