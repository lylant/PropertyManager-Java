package PropertyManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * This class provides a search functionality.
 */

public class SearchUtility {
    /**
     * Search the clients that their name is containing specific search keyword.
     * The search would return all and any clients whose name included the search keyword.
     *
     * @param clients - the arraylist of all clients
     * @param searchRaw - search keyword
     * @return the arraylist of clients matched to search
     */
    private static ArrayList<Client> filterClientsByName(ArrayList<Client> clients, String searchRaw) {

        // convert all characters in the search keyword to upper case
        String search = searchRaw.toUpperCase();

        // predicates for the filter, check whether if the name contains the search keyword or not
        Predicate<Client> filterFirstName = c -> c.getFirstName().toUpperCase().contains(search);
        Predicate<Client> filterLastName = c -> c.getLastName().toUpperCase().contains(search);

        // search all match case and store them in the arraylist
        ArrayList<Client> searchResults = new ArrayList<>(clients.stream()
                .filter(filterFirstName.or(filterLastName))
                .collect(Collectors.toList()));

        return searchResults;
    }


    /**
     * Search the properties that their address is containing specific search keyword.
     * The search would return all and any properties whose address included the search keyword.
     *
     * @param properties - the arraylist of all properties
     * @param searchRaw - search keyword
     * @return the arraylist of properties matched to search
     */
    private static ArrayList<Property> filterPropertiesByAddress(ArrayList<Property> properties, String searchRaw) {

        // convert all characters in the search keyword to upper case
        String search = searchRaw.toUpperCase();

        // predicates for the filter, check whether if the address contains the search keyword or not
        Predicate<Property> filterStreet = p -> p.getStreet().toUpperCase().contains(search);
        Predicate<Property> filterSuburb = p -> p.getSuburb().toUpperCase().contains(search);
        Predicate<Property> filterState = p -> p.getState().toUpperCase().contains(search);
        Predicate<Property> filterPostcode = p -> p.getPostcode().equals(search);

        // search all match case and store them in the arraylist
        ArrayList<Property> searchResults = new ArrayList<>(properties.stream()
                .filter(filterStreet.or(filterSuburb).or(filterState).or(filterPostcode))
                .collect(Collectors.toList()));

        return searchResults;
    }


    /**
     * Search the properties that their postcode is equal to specific search keyword.
     * The search would return all and any properties whose postcode is identical the search keyword.
     *
     * @param properties - the arraylist of all properties
     * @param searchRaw - search keyword
     * @return the arraylist of properties matched to search
     */
    private static ArrayList<Property> filterPropertiesByPostcode(ArrayList<Property> properties, String searchRaw) {

        // convert all characters in the search keyword to upper case
        String search = searchRaw.toUpperCase();

        // predicate for the filter, check whether if the postcode equals the search keyword or not
        Predicate<Property> filterPostcode = p -> p.getPostcode().equals(search);

        // search all match case and store them in the arraylist
        ArrayList<Property> searchResults = new ArrayList<>(properties.stream()
                .filter(filterPostcode)
                .collect(Collectors.toList()));

        return searchResults;
    }

    
    /**
     * Search the clients that their name is containing specific search keyword. The user will
     * be allowed to enter the search keyword until they get at least one result with the keyword.
     *
     * @param clients - the arraylist of all clients
     * @param kb - the Scanner instance to get a user input via a keyboard
     * @return the arraylist of clients matched to search
     */
    public static ArrayList<Client> searchClientsByName(ArrayList<Client> clients, Scanner kb) {

        // initialize local variables:
        ArrayList<Client> searchResults = null; // the arraylist to store the search results
        String searchKeyword = null; // the search keyword entered by the user

        do {
            if (searchResults != null) // this will be ignored for the first attempt
                System.out.println("\n[!] Your search - \"" + searchKeyword + "\" - did not match any records.");
            System.out.print("\nEnter the search keyword: ");
            searchKeyword = kb.nextLine();
            searchResults = filterClientsByName(clients, searchKeyword);
        } while (searchResults.size() == 0);

        return searchResults;
    }
    

    /**
     * Search the properties that their address is containing specific search keyword. The user will
     * be allowed to enter the search keyword until they get at least one result with the keyword.
     *
     * @param properties - the arraylist of all properties
     * @param kb - the Scanner instance to get a user input via a keyboard
     * @return the arraylist of properties matched to search
     */
    public static ArrayList<Property> searchPropertiesByAddress(ArrayList<Property> properties, Scanner kb) {

        // initialize local variables:
        ArrayList<Property> searchResults = null; // the arraylist to store the search results
        String searchKeyword = null; // the search keyword entered by the user

        do {
            if (searchResults != null) // this will be ignored for the first attempt
                System.out.println("\n[!] Your search - \"" + searchKeyword + "\" - did not match any records.");
            System.out.print("\nEnter the search keyword: ");
            searchKeyword = kb.nextLine();
            searchResults = filterPropertiesByAddress(properties, searchKeyword);
        } while (searchResults.size() == 0);

        return searchResults;
    }


    /**
     * Search the properties that their postcode is equal to specific search keyword. The user will
     * be allowed to enter the search keyword until they get at least one result with the keyword.
     *
     * @param properties - the arraylist of all properties
     * @param kb - the Scanner instance to get a user input via a keyboard
     * @return the arraylist of properties matched to search
     */
    public static ArrayList<Property> searchPropertiesByPostcode(ArrayList<Property> properties, Scanner kb) {

        // initialize local variables:
        ArrayList<Property> searchResults = null; // the arraylist to store the search results
        String searchKeyword = "0000"; // the search keyword entered by the user

        do {
            if (!Validator.validatePostcode(searchKeyword)) // invalid input
                System.out.println("\n[!] Invalid postcode. Please enter 4 digit numbers.");
            else if (searchResults != null) // this will be ignored for the first attempt
                System.out.println("\n[!] Your search - \"" + searchKeyword + "\" - did not match any records.");
            System.out.print("\nEnter the postcode: ");
            searchKeyword = kb.nextLine();
            searchResults = filterPropertiesByPostcode(properties, searchKeyword);
        } while (searchResults.size() == 0);

        return searchResults;
    }
    
    
    /**
     * Allows the user to select a specific property from the search results.
     *
     * @param search - the arraylist of properties matched to search
     * @param kb - the Scanner instance to get a user input via a keyboard
     * @return the propertyID of the selected property
     */
    public static int selectPropertyFromSearch(ArrayList<Property> search, Scanner kb) {

        // declare local variables:
        int propertyID = -1; // user input for the selection
        boolean invalidSelect = false; // flag variable for the validity of selection

        // display the search results
        ViewUtility.displaySearchResult(search);

        // Select the property
        System.out.println("\nPlease select the property for which the rent was collected.");

        do {
            if (invalidSelect) // invalid input, print the error message
                System.out.println("\n[!] Invalid selection. Please select the property in the search results.");
            System.out.print("\nEnter the property ID: ");

            try {
                propertyID = kb.nextInt();
                kb.nextLine(); // consume "\n"
            } catch (InputMismatchException exception) {
                propertyID = -1; // replace the select variable with no case match value
                kb.nextLine(); // consume "\n"
            }

            invalidSelect = true; // flag the invalidity for the next loop
        } while (!Validator.validateSelectSearch(search, propertyID));

        return propertyID;
    }
}
