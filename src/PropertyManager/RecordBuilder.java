package PropertyManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RecordBuilder {

    public static ArrayList<Rent> recordRent
            (ArrayList<Property> properties, ArrayList<Rent> rents, Scanner kb) {

        // initialize local variables:

        // the hashmap of clients to convert clientID to their name
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();
        ArrayList<Property> searchResults = null; // the arraylist to store the search results
        int propertyID = -1; // the propertyID of the property the rent collection to be added
        String searchConfirm = "N"; // the user input to confirm the selecting the property
        int weeks = -1; // the user input for the number of weeks the rent was collected
        boolean invalidWeeks = false; // the flag variable for the validity of weeks
        DateTimeFormatter dateFormat = EnvManager.getDateTimeFormatter(); // date format
        String date = LocalDate.now().format(dateFormat); // String containing of the current date

        
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

        
        // confirm the selection
        System.out.println("\nAre you sure to add a record of rent collection to this property?");
        do {
            if (!Validator.validateYesOrNo(searchConfirm)) // invalid input, print the error message
                System.out.println("\n[!] Invalid input. Please answer with Y/N.");
            System.out.print("\nEnter Y/N: ");

            searchConfirm = kb.nextLine();

            if (searchConfirm.equalsIgnoreCase("N")) // "N": return to main menu
                return rents;
        } while (!searchConfirm.equalsIgnoreCase("Y"));

        
        // ask the user how many weeks
        System.out.println("\nHow many weeks of rent was collected for the property?");
        do {
            if (invalidWeeks) // invalid input, print the error message
                System.out.println("\n[!] Invalid number of weeks. Please enter a positive integer.");
            System.out.print("\nEnter the number of weeks: ");

            try {
                weeks = kb.nextInt();
                kb.nextLine(); // consume "\n"
            } catch (InputMismatchException exception) {
                weeks = -1; // replace the weeks with invalid value
                kb.nextLine(); // consume "\n"
            }

            invalidWeeks = true; // flag the invalidity for the next loop
        } while (!Validator.validateWeeks(weeks));


        // instantiate a new Rent object with given data
        Rent newRent = new Rent
                (propertyID, (double)(weeks*propertiesHashMap.get(propertyID).getRentWeekly()), date);


        // add the record of new Rent collection to the arraylist of Rents
        rents.add(newRent);
        DatabaseUtility.setIsSaved(false); // flag the change occurs
        
        return rents;
    }
}
