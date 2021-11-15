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


    public static boolean validateDescription(String descr) {
        String regEx = "[a-zA-Z0-9][a-zA-Z0-9\\s-_']{0,19}";
        return (Pattern.matches(regEx, descr));
    }
}
