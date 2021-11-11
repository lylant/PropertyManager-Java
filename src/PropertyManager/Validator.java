package PropertyManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class provides the validation functionalities for the user input.
 */

public class Validator {
    
    /**
     * Determine whether if the input selection exists on the search result list or not. Returns true
     * if there is a match case in the arraylist. The arraylist should be sorted by ID before the validation.
     * Overriding method of Comparator is inspired from: https://stackoverflow.com/a/34646172
     *
     * @param search - the arraylist of search results
     * @param select - the user input for the selection
     * @return the validity
     */
    public static boolean validateSelectSearch(ArrayList<Property> search, int select) {

        int index = -1;

        // perform a binary search to determine if the match case exists or not
        // return value will be >= 0 if and only if the key is found
        index = Collections.binarySearch
                (search, new Property(select), new Comparator<Property>() {
                    @Override
                    public int compare(Property a, Property b) {
                        return Integer.compare(a.getID(), b.getID());
                    }
                });

        return (index >= 0) ? true : false;
    }
}
