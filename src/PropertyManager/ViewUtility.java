package PropertyManager;

import java.util.HashMap;

/*
 * This class provides some displaying the information functionalities. Displaying the table of
 * info, a detail of specific class, on-screen reports, etc. methods will be modularised and be
 * placed in this class.
 */

public class ViewUtility {

    /**
     * Display the detail of a specific property
     *
     * @param property - the property to be displayed
     */
    public static void displayPropertyDetail(Property property) {
        // the hashmap of clients to convert clientID to their name
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();

        // info box title
        System.out.println("\n [*] The Detail of the Selected Property");
        // info box border
        for (int i=0; i < 58; i++)
            System.out.print("-");
        System.out.println();
        // info box body
        System.out.println(property);
        // info box border
        for (int i=0; i < 58; i++)
            System.out.print("-");
        System.out.println();
    }


    /**
     * Display the detail of a specific rent collection event
     *
     * @param rent - the rent collection event to be displayed
     */
    public static void displayRentDetail(Rent rent) {
        // info box title
        System.out.println("\n [*] The Summary of the Rent Collection Transaction");
        // info box border
        for (int i=0; i < 66; i++)
            System.out.print("-");
        System.out.println();
        // info box body
        System.out.println(rent);
        // info box border
        for (int i=0; i < 66; i++)
            System.out.print("-");
        System.out.println();
    }
}