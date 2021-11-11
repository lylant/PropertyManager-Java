package PropertyManager;

import java.util.ArrayList;
import java.util.Scanner;

public class RecordBuilder {

    public static ArrayList<Rent> recordRent
            (ArrayList<Property> properties, ArrayList<Rent> rents, Scanner kb) {

        // initialize local variables:
        ArrayList<Property> searchResults = null; // the arraylist to store the search results
        int propertyID = -1; // the propertyID of the property the rent collection to be added

        // display the menu welcome message
        MenuUtility.displayRecordRent();

        // search the property
        System.out.println("\nYou need to choose a specific property to add a new rent collection record.");
        System.out.println("You can find the property by searching the address of the property.");
        searchResults = SearchUtility.searchPropertiesByAddress(properties, kb);

        if (searchResults.size() > 1) { // the search found 2+ properties, allow the user to select one
            propertyID = SearchUtility.selectPropertyFromSearch(searchResults, kb);
        }
        else { // the search found only one property, select the property automatically
            propertyID = searchResults.get(0).getID();
        }

        // display the detail of property
        ViewUtility.displayPropertyDetail(propertyID);

        return rents;
    }
}
