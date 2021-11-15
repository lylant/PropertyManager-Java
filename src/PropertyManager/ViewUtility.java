package PropertyManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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


    /**
     * Display the detail of a specific expense event
     *
     * @param expense - the expense event to be displayed
     */
    public static void displayExpenseDetail(Expense expense) {
        // info box title
        System.out.println("\n [*] The Summary of the Expense Event");
        // info box border
        for (int i=0; i < 68; i++)
            System.out.print("-");
        System.out.println();
        // info box body
        System.out.println(expense);
        // info box border
        for (int i=0; i < 68; i++)
            System.out.print("-");
        System.out.println();
    }
    
    
    public static void requestPortfolioReport(ArrayList<Client> clients, ArrayList<Property> properties
            , ArrayList<Expense> expenses, ArrayList<Rent> rents, Scanner kb) {

        /*
         *  initialize local variables:
         */
        // an array representation of menu options
        String[] menuOptions = EnvManager.getReportOptions();
        // a digit number to choose the exit program option
        int menuOptionExit = EnvManager.getMenuOptionExit();
        // the user input for the menu selection
        int menuOptionInput = -1;
        // the welcome message for the menu selection
        String menuWelcome = "\nThis program provides three types of portpolio report."
                + " Please select the option below: \n";


        // display the menu welcome message
        MenuUtility.displayPortfolioReport();


        // provide a menu functionality
        do {
            MenuUtility.displayMenu(menuOptions, menuWelcome);
            menuOptionInput = MenuUtility.getMenuSelect(menuOptions, kb);

            switch(menuOptionInput) {
                case 1: // specific client, searched by name
                    System.out.println("1");
                    return;
                case 2: // all clients
                    System.out.println("2");
                    return;
                case 3: // specified postcode
                    System.out.println("3");
                    return;
                case 0: // exit to main menu
                    return;
                default: // no case match, an impossible case
                    System.out.println("[!] Unidentified Error in ViewUtility.requestPortfolioReport().");
                    break;
            }
        } while (menuOptionInput != menuOptionExit);
    }
}