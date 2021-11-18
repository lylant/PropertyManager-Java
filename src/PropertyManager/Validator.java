package PropertyManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

/*
 * This class provides the validation functionalities for the user input.
 */

public class Validator {

    /**
     * Validate the menu selection.
     *
     * @param option - the user input for the menu selection
     * @param optionMin - a minimum digit number of the option list
     * @param optionMax - a maximum digit number of the option list
     * @return the validity
     */
    public static boolean validateSelectMenu(int option, int optionMin, int optionMax) {
        return !(option < optionMin || option > optionMax);
    }


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

        return (index >= 0);
    }


    /**
     * Determine whether if the properties with specific clientID exists or not. Returns true if there
     * is a match cast in the arraylist. The arraylist should be sorted by clientID before the validation.
     * Overriding method of Comparator is inspired from: https://stackoverflow.com/a/34646172
     *
     * @param properties - the arraylist of all properties
     * @param clientID - the clientID of interested
     * @return the validity
     */
    public static boolean validateOwnerHasProperty(ArrayList<Property> properties, int clientID) {

        int index = -1;

        // perform a binary search to determine if the match case exists or not
        // return value will be >= 0 if and only if the key is found
        index = Collections.binarySearch
                (properties, new Property(-1, clientID), new Comparator<Property>() {
                    @Override
                    public int compare(Property a, Property b) {
                        return Integer.compare(a.getClientID(), b.getClientID());
                    }
                });

        return (index >= 0);

    }


    /**
     * Determine whether if a specific client has a record of rent collection or expense. Returns true
     * if there is at least one record in the arraylist. The arraylist of property should be sorted by
     * clientID before the validation, the other arraylists should be sorted by propertyID.
     * Overriding method of Comparator is inspired from: https://stackoverflow.com/a/34646172
     *
     * @param properties - the arraylist of all properties, sorted by clientID
     * @param expenses - the arraylist of all expenses, sorted by propertyID
     * @param rents - the arraylist of all rents, sorted by propertyID
     * @param clientID - the clientID of interested
     * @return the validity
     */
    public static boolean validateOwnerHasRentOrExpense(ArrayList<Property> properties, ArrayList<Expense> expenses
            , ArrayList<Rent> rents, int clientID) {

        // the variables for checking the record exists or not
        double rentCheck = 0;
        double expenseCheck = 0;

        for (int i=0; i < properties.size(); i++) {
            if (properties.get(i).getClientID() == clientID) {
                rentCheck += properties.get(i).getTotalRent(rents);
                expenseCheck += properties.get(i).getTotalExpenses(expenses);
            }
            if (properties.get(i).getClientID() > clientID)
                break;
        }

        return ((rentCheck != 0) || (expenseCheck != 0));
    }
    
    
    /**
     * Validate Y/N input. Returns true if the input is one of "Y", "y", "N", "n".
     *
     * @param input - the user input for Y/N question
     * @return the validity
     */
    public static boolean validateYesOrNo(String input) {
        return (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) ? true : false;
    }


    /**
     * Validate the number of weeks rent was collected. Valid if the input is positive.
     *
     * @param weeks - the number of weeks to be validated
     * @return the validity
     */
    public static boolean validateWeeks(int weeks) {
        return (weeks > 0);
    }


    /**
     * Validate the description of the expense event. This method would check the String with RegEx.
     *
     * @param descr - the description to be validated
     * @return the validity
     */
    public static boolean validateDescription(String descr) {
        String regEx = "[a-zA-Z0-9][a-zA-Z0-9\\s-_']{0,49}";
        return (Pattern.matches(regEx, descr));
    }


    /**
     * Validate the postcode input. This method would check the String with RegEx.
     *
     * @param postcode - the postcode to be validated
     * @return the validity
     */
    public static boolean validatePostcode(String postcode) {
        String regEx = "[0-9]{4}";
        return (Pattern.matches(regEx, postcode));
    }


    /**
     * Validate the cost of the expense event. Valid if the input is positive.
     *
     * @param cost - the cost of the expense event to be validated
     * @return the validity
     */
    public static boolean validateCost(double cost) {
        return (cost > 0);
    }
}
