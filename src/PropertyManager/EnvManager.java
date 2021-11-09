package PropertyManager;

/*
 * This class contains the program's starting up data. The main purpose of this class is to avoid
 * hard-coded values in the program scripts. Only be used for Startup phase of the program, should
 * not be used further process of the program.
 */

public class EnvManager {

    // default source file names to be read
    private static String fileClients = "clients.txt";
    private static String fileProperties = "properties.txt";
    private static String fileExpenses = "expenses.txt";
    private static String fileRents = "rents.txt";

    // an array representation of menu options
    private static String[] menuOptions = {"Record Rent Collection",
            "Record Expense",
            "Generate Portfolio Report",
            "Save Changes",
            "Exit the Program"};

    private static int menuOptionMin = 1;  // a minimum digit number of the option list
    private static int menuOptionExit = 0; // a digit number to choose the exit program option


    public static String getFileClients() {
        return fileClients;
    }

    public static String getFileProperties() {
        return fileProperties;
    }

    public static String getFileExpenses() {
        return fileExpenses;
    }

    public static String getFileRents() {
        return fileRents;
    }

    public static String[] getMenuOptions() {
        return menuOptions;
    }

    public static int getMenuOptionMin() {
        return menuOptionMin;
    }

    public static int getMenuOptionExit() {
        return menuOptionExit;
    }
}
