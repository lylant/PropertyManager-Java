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
     * @param propertyID - the unique numeric identifier for the property
     */
    public static void displayPropertyDetail(int propertyID) {
        // the hashmap of clients to convert clientID to their name
        HashMap<Integer, Property> propertiesHashMap = HashMapContainer.getPropertiesHashMap();

        // info box title
        System.out.println("\n [*] The Detail of the Selected Property");
        // info box border
        for (int i=0; i < 58; i++)
            System.out.print("-");
        System.out.println();
        // info box body
        System.out.println(propertiesHashMap.get(propertyID));
        // info box border
        for (int i=0; i < 58; i++)
            System.out.print("-");
        System.out.println();
    }
}
