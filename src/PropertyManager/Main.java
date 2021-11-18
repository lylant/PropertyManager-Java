package PropertyManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
     *  GLOBAL VARIABLES
     */

    // Scanner object for the keyboard input
    private static Scanner kb = new Scanner(System.in);

    // instantiate arraylist objects to bind references of each class
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Property> properties = new ArrayList<>();
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<Rent> rents = new ArrayList<>();


    public static void main(String[] args) {

        /*
         * Loading Sequences
         */

        DatabaseUtility.loadingClients(clients, kb);
        if(clients.size() != 0) { // skip the next step if the previous loading is failed
            HashMapContainer.buildClientsHashMap(clients);
            DatabaseUtility.loadingProperties(properties, kb);
        }
        if(properties.size() != 0) {
            HashMapContainer.buildPropertiesHashMap(properties);
            DatabaseUtility.loadingExpenses(expenses, kb);
        }
        if(expenses.size() != 0)
            DatabaseUtility.loadingRents(rents, kb);

        // loading failed: terminate the program
        if(rents.size() == 0) {
            System.out.println("Good Bye.");
            return;
        }


        /*
         * prepare local variables for the main menu:
         */
        // the user input for the menu selection
        int menuOptionInput = -1;
        // the welcome message for the menu selection
        String menuWelcome = "\nWelcome, administrator. Please select the option below: \n";
        // an array representation of menu options
        String[] menuOptions = EnvManager.getMenuOptions();
        // a digit number to choose the exit program option
        int menuOptionExit = EnvManager.getMenuOptionExit();


        /*
         * Main Functionality Ready
         */

        // display the program title artwork
        MenuUtility.displayTitle();

        // the main menu loop
        do {
            MenuUtility.displayMenu(menuOptions, menuWelcome);
            menuOptionInput = MenuUtility.getMenuSelect(menuOptions, kb);

            switch(menuOptionInput) {
                case 1: // Record Rent Collection
                    RecordBuilder.recordRent(properties, rents, kb);
                    break;
                case 2: // Record Expense
                    RecordBuilder.recordExpense(properties, expenses, kb);
                    break;
                case 3: // Portfolio Report
                    requestPortfolioReport();
                    break;
                case 4: // Save
                    DatabaseUtility.saveChanges();
                    break;
                case 0: // Exit the Program
                    System.out.println("Good Bye.");
                    break;
                default: // no case match, an impossible case
                    System.out.println("[!] Unidentified Error in MainProgram.main().");
                    break;
            }
        } while (menuOptionInput != menuOptionExit);
    }


    /**
     * Display the menu to generate the on-screen portfolio report. The user is allowed to select
     * the available portfolio report option from the menu. This method is used to call and provide
     * proper parameters to the methods to generate an actual portfolio report.
     */
    public static void requestPortfolioReport() {

        /*
         *  initialize local variables:
         */
        // an array representation of menu options
        String[] menuReportOptions = EnvManager.getReportOptions();
        // the user input for the menu selection
        int menuOptionInput = -1;
        // the digit number to choose the exit program option
        int menuOptionExit = EnvManager.getMenuOptionExit();
        // the welcome message for the menu selection
        String menuWelcome = "\nThis program provides three types of portpolio report."
                + " Please select the option below: \n";


        // display the menu welcome message
        MenuUtility.displayPortfolioReport();


        // provide a menu functionality
        do {
            MenuUtility.displayMenu(menuReportOptions, menuWelcome);
            menuOptionInput = MenuUtility.getMenuSelect(menuReportOptions, kb);

            switch(menuOptionInput) {
                case 1: // specific client, searched by name
                    ViewUtility.displayReportSpecificClient(clients, properties, expenses, rents, kb);
                    return;
                case 2: // all clients
                    ViewUtility.displayReportAllClient(clients, properties, expenses, rents, kb);
                    return;
                case 3: // specified postcode
                    ViewUtility.displayReportSpecificPostcode(properties, expenses, rents, kb);
                    return;
                case 0: // exit to main menu
                    return;
                default: // no case match, an impossible case
                    System.out.println("[!] Unidentified Error in MainProgram.requestPortfolioReport().");
                    break;
            }
        } while (menuOptionInput != menuOptionExit);
    }
}