package PropertyManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
     *  GLOBAL VARIABLES
     */

    // Scanner object for the keyboard input
    static Scanner kb = new Scanner(System.in);

    // instantiate arraylist objects to bind references of each class
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Property> properties = new ArrayList<>();
    static ArrayList<Expense> expenses = new ArrayList<>();
    static ArrayList<Rent> rents = new ArrayList<>();

    static boolean isSaved = true;


    public static void main(String[] args) {

        /*
         * Prepare the local variables
         */

        // default source file names to be read
        String fileClients = "clients.txt";
        String fileProperties = "properties.txt";
        String fileExpenses = "expenses.txt";
        String fileRents = "rents.txt";

        // an array representation of menu options
        String[] menuOptions = {"Record Rent Collection",
                "Record Expense",
                "Generate Portfolio Report",
                "Save Changes",
                "Exit the Program"};
        int menuOptionInput = -1; // the user input for the menu selection
        int menuOptionExit = 0;   // a digit number to choose the exit program option


        /*
         * Loading Process
         */
        clients = DatabaseUtility.loadingClients(clients, fileClients);
        if (clients.size() != 0) // skip the next step if the previous loading is failed
            properties = DatabaseUtility.loadingProperties(properties, fileProperties);
        if (properties.size() != 0)
            expenses = DatabaseUtility.loadingExpenses(expenses, fileExpenses);
        if (expenses.size() != 0)
            rents = DatabaseUtility.loadingRents(rents, fileRents);

        // loading failed: terminate the program
        if (rents.size() == 0) {
            System.out.println("Good Bye.");
            return;
        }


        Menu.displayTitle();

        do {
            Menu.displayMenu(menuOptions, menuOptionExit);
            menuOptionInput = Menu.getMenuSelect(menuOptions, menuOptionExit);

            switch (menuOptionInput) {
            }

        } while (menuOptionInput != 0);
    }
}
