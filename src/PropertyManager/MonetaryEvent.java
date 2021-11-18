package PropertyManager;

import java.util.HashMap;

/*
 * This class represent a single monetary event for a specific property, such as rent collection
 * or expense event. Expense and Rent class will be extended from this class.
 */

public class MonetaryEvent {

    // DATA FIELDS
    private int propertyID; // the ID of the property
    private String date;    // the date on which the event occured or recorded

    // As this class is not used for processing date, the date is String datatype for the simplicity
    // the date is in yyyy-MM-dd format


    /**
     * Non-Argument Constructor
     */
    public MonetaryEvent() {
        propertyID = -1;
        date = "2000-01-01";
    }

    /**
     * Constructor
     *
     * @param ID - the ID of the property
     * @param date - the date on which the event occured or recorded
     */
    public MonetaryEvent(int ID, String date) {
        propertyID = ID;
        this.date = date;
    }


    /**
     * @return the ID of the property
     */
    public int getPropertyID() {
        return propertyID;
    }

    /**
     * @return the date on which the event occured or recorded
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the full address of the property
     */
    public String getPropertyAddress() {
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();
        return propertiesHashMap.get(propertyID).getAddress().toString();
    }

    /**
     * @return the full name of the property's owner
     */
    public String getPropertyOwnerName() {
        HashMap<Integer, Client> clientsHashMap = HashMapContainer.getClientsHashMap();
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();
        return clientsHashMap.get(propertiesHashMap.get(propertyID).getClientID()).getFullName();
    }


    /**
     * @param ID - the ID of the property
     */
    public void setPropertyID(int ID) {
        propertyID = ID;
    }

    /**
     * @param date - the date on which the event occured or recorded
     */
    public void setDate(String date) {
        this.date = date;
    }
}
