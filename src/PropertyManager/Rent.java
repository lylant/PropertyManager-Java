package PropertyManager;

import java.text.DecimalFormat;
import java.util.HashMap;

// This class represents a single rent collection event.

public class Rent extends MonetaryEvent {

    // decimal format for the monetary value
    private static DecimalFormat df = EnvManager.getDecimalFormat();
    
    // DATA FIELDS
    private double rentAmount; // the monetary amount of rent collected


    /**
     * Non-Argument Constructor
     */
    public Rent() {
        super();
        rentAmount = -1;
    }

    /**
     * Constructor
     *
     * @param ID - the ID of the property for which the rent was collected
     * @param rent - the monetary amount of rent collected
     * @param date - the date on which the rent was collected
     */
    public Rent(int ID, double rent, String date) {
        super(ID, date);
        rentAmount = rent;
    }


    /**
     * @return the summary details of the rent collection event
     */
    public String toString() {
        // the hashmaps to convert foreign IDs to meaningful information
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();

        String toStr = "Property Address:  " + super.getPropertyAddress()
                + "\nProperty Owner:\t   " + super.getPropertyOwnerName()
                + "\nNumber of Weeks:   "
                + (int)(rentAmount/propertiesHashMap.get(super.getPropertyID()).getRentWeekly())
                + "\nMonetary Amount:   $ " + df.format(rentAmount)
                + "\nCurrent Date:\t   " + super.getDate();
        return toStr;
    }


    /**
     * @return the monetary amount of rent collected
     */
    public double getRentAmount() {
        return rentAmount;
    }

    /**
     * @param rent - the monetary amount of rent collected
     */
    public void setRentAmount(double rent) {
        rentAmount = rent;
    }
}
