package PropertyManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RecordBuilder {

    /**
     * Add a rent collection record to specific property. The user can find and select the property
     * by entering the property's address. The user is allowed to enter the number of weeks the rent
     * was collected, however, the date of the rent collection will be recorded as the current date.
     *
     * @param properties - the arraylist of all properties
     * @param rents - the arraylist of all rents to be updated
     * @param kb - the Scanner instance to get a user input via a keyboard
     */
    public static void recordRent(ArrayList<Property> properties, ArrayList<Rent> rents, Scanner kb) {

        /*
         *  initialize local variables:
         */
        // the hashmap for properties, for getting a specific Property instance from its ID
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();
        // the arraylist to store the search results
        ArrayList<Property> searchResults = null;
        // the propertyID of the property the rent collection to be added
        int propertyID = -1;
        // the user input to confirm the selecting the property
        String searchConfirm = "N";
        // number of weeks
        int weeks = -1; // the user input for the number of weeks the rent was collected
        boolean invalidWeeks = false; // the flag variable for the validity of weeks
        // current date
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
        ViewUtility.displayPropertyDetail(propertiesHashMap.get(propertyID));

        
        // confirm the selection
        System.out.println("\nAre you sure to add a record of rent collection to this property?");
        do {
            if (!Validator.validateYesOrNo(searchConfirm)) // invalid input, print the error message
                System.out.println("\n[!] Invalid input. Please answer with Y/N.");
            System.out.print("\nEnter Y/N: ");

            searchConfirm = kb.nextLine();

            if (searchConfirm.equalsIgnoreCase("N")) // "N": return to main menu
                return;
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


        // display the transaction report
        ViewUtility.displayRentDetail(newRent);
        System.out.print("\n (Press ENTER key to return to main menu)");
        kb.nextLine();
        return;
    }


    public static ArrayList<Expense> recordExpense
            (ArrayList<Property> properties, ArrayList<Expense> expenses, Scanner kb) {

        /*
         *  initialize local variables:
         */
        // the hashmap for properties, for getting a specific Property instance from its ID
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();
        // the arraylist to store the search results 
        ArrayList<Property> searchResults = null;
        // the propertyID of the property the rent collection to be added
        int propertyID = -1;
        // the user input to confirm the selecting the property
        String searchConfirm = "N";
        // number of weeks
        //int weeks = -1; // the user input for the number of weeks the rent was collected
        //boolean invalidWeeks = false; // the flag variable for the validity of weeks
        // current date
        DateTimeFormatter dateFormat = EnvManager.getDateTimeFormatter(); // date format
        String date = LocalDate.now().format(dateFormat); // String containing of the current date


        // display the menu welcome message
        MenuUtility.displayRecordExpense();


        return expenses;
    }
}
