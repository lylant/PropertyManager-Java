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
            System.out.println("\n[!] Loading failed. If you did not choose to exit, "
                    + "one or more of your files might be empty.");
            System.out.println("\nGood Bye.");
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
                    menuOptionInput = exitProgram(menuOptionExit);
                    break;
                default: // no case match, an impossible case
                    System.out.println("[!] Unidentified Error in MainProgram.main().");
                    break;
            }
        } while (menuOptionInput != menuOptionExit);

        kb.close();
    }


    /**
     * Display the menu to generate the on-screen portfolio report. The user is allowed to select
     * the available portfolio report option from the menu. This method is used to call and provide
     * proper parameters to the methods to generate an actual portfolio report.
     */
    private static void requestPortfolioReport() {

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


    /**
     * Terminate the program. If there is unsaved changes since the last save, the method will inform
     * the user and give the chance to return to the main menu.
     *
     * @param menuOptionExit - the digit number to choose the exit program option
     * @return the determining integer. If the user is sure to quit the program, will be same as
     * menuOptionExit, if the user want to go back to menu, will be any number which is not matched
     * any option value.
     */
    private static int exitProgram(int menuOptionExit) {
        // the user input to confirm the exit
        String exitConfirm = "Y";

        // if unsaved change exists, ask confirm the exit
        if(!DatabaseUtility.getIsSaved()) {
            System.out.println("\n [!] You have unsaved changes since the last save.");
            System.out.println(" [!] Do you want to return to the main menu?");

            do {
                if (!Validator.validateYesOrNo(exitConfirm)) // invalid input, print the error message
                    System.out.println("\n[!] Invalid input. Please answer with Y/N.");
                System.out.print("\nEnter Y/N: ");

                exitConfirm = kb.nextLine();

                if (exitConfirm.equalsIgnoreCase("Y")) // "Y": return to main menu
                    return -1;
            } while (!exitConfirm.equalsIgnoreCase("N"));
        }

        System.out.println("\nGood Bye.");
        return menuOptionExit;
    }
}