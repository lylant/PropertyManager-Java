package PropertyManager;

/*
 * This class contains the program's starting up data. The main purpose of this class is to avoid
 * hard-coded values in the program scripts. Only be used for Startup phase of the program, should
 * not be used further process of the program.
 */

import java.time.format.DateTimeFormatter;

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

    // DateTimeFormatter for this program
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * @return the default source file name of clients
     */
    public static String getFileClients() {
        return fileClients;
    }

    /**
     * @return the default source file name of properties
     */
    public static String getFileProperties() {
        return fileProperties;
    }

    /**
     * @return the default source file name of expenses
     */
    public static String getFileExpenses() {
        return fileExpenses;
    }

    /**
     * @return the default source file name of rents
     */
    public static String getFileRents() {
        return fileRents;
    }

    /**
     * @return the array representation of menu options
     */
    public static String[] getMenuOptions() {
        return menuOptions;
    }

    /**
     * @return the minimum digit number of the option list
     */
    public static int getMenuOptionMin() {
        return menuOptionMin;
    }

    /**
     * @return the digit number to choose the exit program option
     */
    public static int getMenuOptionExit() {
        return menuOptionExit;
    }

    /**
     * @return the DateTimeFormatter for this program
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return dateFormat;
    }
}
