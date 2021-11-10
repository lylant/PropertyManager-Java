package PropertyManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
     *  GLOBAL VARIABLES
     */

    // Scanner object for the keyboard input
    private static Scanner kb = new Scanner(System.in);

    // default source file names to be read
    private static String fileClients = EnvManager.getFileClients();
    private static String fileProperties = EnvManager.getFileProperties();
    private static String fileExpenses = EnvManager.getFileExpenses();
    private static String fileRents = EnvManager.getFileRents();

    // instantiate arraylist objects to bind references of each class
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Property> properties = new ArrayList<>();
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<Rent> rents = new ArrayList<>();

    // attributes for the menu selection
    private static String[] menuOptions = EnvManager.getMenuOptions(); // array of menu options
    private static int menuOptionExit = EnvManager.getMenuOptionExit(); // digit of exit option

    static boolean isSaved = true;


    public static void main(String[] args) {

        /*
         * Loading Process
         */
        clients = DatabaseUtility.loadingClients(clients, kb);
        if(clients.size() != 0) // skip the next step if the previous loading is failed
            properties = DatabaseUtility.loadingProperties(properties, kb);
        if(properties.size() != 0)
            expenses = DatabaseUtility.loadingExpenses(expenses, kb);
        if(expenses.size() != 0)
            rents = DatabaseUtility.loadingRents(rents, kb);

        // loading failed: terminate the program
        if (rents.size() == 0) {
            System.out.println("Good Bye.");
            return;
        }


        Menu.displayTitle();

        int menuOptionInput = -1;   // the user input for the menu selection

        do {

            Menu.displayMenu(menuOptions);
            menuOptionInput = Menu.getMenuSelect(menuOptions, kb);

            switch(menuOptionInput) {

                case 1:
                    // Record Rent Collection
                    System.out.println("1");
                    break;

                case 2:
                    // Record Expense
                    System.out.println("2");
                    break;

                case 3:
                    // Portfolio Report
                    System.out.println("3");
                    break;

                case 4:
                    // Save
                    System.out.println("4");
                    break;

                case 0:
                    // Exit the Program
                    System.out.println("Good Bye.");
                    break;

                default: // no case match
                    System.out.println("Please select a option below with a single digit number.");
                    break;
            }


        } while (menuOptionInput != menuOptionExit);
    }
}
