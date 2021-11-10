package PropertyManager;

/*
 * This class provides a search functionality.
 */

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Search {
    /**
     * Search the clients that their name is containing specific search keyword.
     * The search would return all and any clients whose name included the search keyword.
     *
     * @param clients - the arraylist of all clients
     * @param searchRaw - search keyword
     * @return the arraylist of clients matched to search
     */
    public static ArrayList<Client> searchClientsByName
    (ArrayList<Client> clients, String searchRaw) {

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
    public static ArrayList<Property> searchPropertiesByAddress
    (ArrayList<Property> properties, String searchRaw) {

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
    public static ArrayList<Property> searchPropertiesByPostcode
    (ArrayList<Property> properties, String searchRaw) {

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
}
