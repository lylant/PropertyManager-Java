package PropertyManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class provides the sort functionality using Collection.sort() method with Comparator.
 * The methods in this class are heavily inspired from: https://stackoverflow.com/a/34646172
 */

public class SortUtility {
    
    /**
     * Sort the arraylist of Properties by their ID as an ascending order.
     *
     * @param properties - the arraylist of Properties to be sorted
     * @return the sorted arraylist of Properties
     */
    public static void sortPropertyByID(ArrayList<Property> properties) {
        Collections.sort(properties, new Comparator<Property>() {
            @Override
            public int compare(Property a, Property b) {
                return Integer.compare(a.getID(), b.getID());
            }
        });
        return;
    }
}
