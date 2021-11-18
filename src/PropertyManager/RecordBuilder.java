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
        SortUtility.sortPropertyByID(searchResults); // sort the search result by their IDs

        if (searchResults.size() > 1) { // the search found 2+ properties, allow the user to select one
            propertyID = SearchUtility.selectPropertyFromSearch(searchResults, kb);
        }
        else { // the search found only one property, select the property automatically
            propertyID = searchResults.get(0).getID();
        }

        
        // display the detail of property
        ViewUtility.displayPropertyDetail(propertiesHashMap.get(propertyID));

        
        // confirm the selection
        System.out.println("\nAre you sure to add a record of a rent collection to this property?");
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
        DatabaseUtility.storeNewRent(newRent); // store to the separated arraylist as well
        DatabaseUtility.setIsSaved(false); // flag the change occurs


        // display the transaction report
        ViewUtility.displayRentDetail(newRent);
        System.out.print("\n (Press ENTER key to return to main menu)");
        kb.nextLine();
        return;
    }


    /**
     * Add a expense record to specific property. The user can find and select the property by entering
     * the property's address. The user should enter the description of the expense and the cost of the
     * expense. The date of the expense event will be recorded as the current system date.
     *
     * @param properties - the arraylist of all properties
     * @param expenses - the arraylist of all expenses to be updated
     * @param kb - the Scanner instance to get a user input via a keyboard
     */
    public static void recordExpense(ArrayList<Property> properties, ArrayList<Expense> expenses, Scanner kb) {

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
        // description of the expense
        String descr = ""; // the user input for the description of the expense event
        boolean invalidDescr = false; // the flag variable for the validity of the description
        // the cost of expense
        double cost = -1.00f; // the user input for the cost of the expense
        boolean invalidCost = false; // the flag variable for the validity of the cost
        // current date
        DateTimeFormatter dateFormat = EnvManager.getDateTimeFormatter(); // date format
        String date = LocalDate.now().format(dateFormat); // String containing of the current date


        // display the menu welcome message
        MenuUtility.displayRecordExpense();


        // search the property
        System.out.println("\nYou need to choose a specific property the expense event was incurred.");
        System.out.println("You can find the property by searching the address of the property.");
        searchResults = SearchUtility.searchPropertiesByAddress(properties, kb);
        SortUtility.sortPropertyByID(searchResults); // sort the search result by their IDs


        // display the detail of property
        ViewUtility.displayPropertyDetail(propertiesHashMap.get(propertyID));


        // confirm the selection
        System.out.println("\nAre you sure to add a record of an expense event to this property?");
        do {
            if (!Validator.validateYesOrNo(searchConfirm)) // invalid input, print the error message
                System.out.println("\n[!] Invalid input. Please answer with Y/N.");

            searchConfirm = kb.nextLine();

            if (searchConfirm.equalsIgnoreCase("N")) // "N": return to main menu
                return;
        } while (!searchConfirm.equalsIgnoreCase("Y"));


        // ask the user to enter description
        System.out.println("\nEnter a simple description of the incurred expense event (e.g. \"fix leaking tap\").");
        do {
            if (invalidDescr) // invalid input, print the error message
                System.out.println("\n[!] Invalid description. Please follow the rule explained below."
                        + "\n * Only alphanumerical letter, whitespace, hyphen, apostrophe, or underline are allowed."
                        + "\n * Must start with alphanumerical letter. Allowed to use maximum 50 letters.");
            System.out.print("\nEnter the description: ");

            descr = kb.nextLine();

            invalidDescr = true; // flag the invalidity for the next loop
        } while (!Validator.validateDescription(descr));


        // ask the user how much the cost
        System.out.println("\nHow much the cost is spent for this expense event?");
        do {
            if (invalidCost) // invalid input, print the error message
                System.out.println("\n[!] Invalid expense cost. Please enter a positive number.");
            System.out.print("\nEnter the cost: ");

            try {
                cost = kb.nextDouble();
                kb.nextLine(); // consume "\n"
            } catch (InputMismatchException exception) {
                cost = -1.00f; // replace the weeks with invalid value
                kb.nextLine(); // consume "\n"
            }

            invalidCost = true; // flag the invalidity for the next loop
        } while (!Validator.validateCost(cost));


        // instantiate a new Expense object with given data
        Expense newExpense = new Expense(propertyID, descr, cost, date);


        // add the record of new Expense collection to the arraylist of Expenses
        expenses.add(newExpense);
        DatabaseUtility.storeNewExpense(newExpense); // store to the separated arraylist as well
        DatabaseUtility.setIsSaved(false); // flag the change occurs


        // display the transaction report
        ViewUtility.displayExpenseDetail(newExpense);
        System.out.print("\n (Press ENTER key to return to main menu)");
        kb.nextLine();
        return;
    }
}
