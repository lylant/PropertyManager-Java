package PropertyManager;

/*
 * This class contains the program's starting up data. The main purpose of this class is to avoid
 * hard-coded values in the program scripts. Only be used for Startup phase of the program, should
 * not be used further process of the program.
 */

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class EnvManager {

    // default source file names to be read
    private static String fileClients = "clients.txt";
    private static String fileProperties = "properties.txt";
    private static String fileExpenses = "expenses.txt";
    private static String fileRents = "rents.txt";

    // an array representation of main menu options
    private static String[] menuOptions = {"Record Rent Collection",
            "Record Expense",
            "Generate Portfolio Report",
            "Save Changes",
            "Exit the Program"};

    // an array representation of portfolio menu options
    private static String[] reportOptions = {"Generate a report for a specific client",
            "Generate a report for all clients",
            "Generate a report for a specified postcode",
            "Exit to Main Menu"};

    private static int menuOptionMin = 1;  // a minimum digit number of the option list
    private static int menuOptionExit = 0; // a digit number to choose the exit program option

    // DecimalFormat for the monetary values
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    // DateTimeFormatter for this program, dates only
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // DateTimeFormatter for this program, for report use
    private static DateTimeFormatter dateFormatDetail =
            DateTimeFormatter.ofPattern("EEEE, yyyy MMMM dd, hh:mm:ssa");



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
     * @return the array representation of main menu options
     */
    public static String[] getMenuOptions() {
        return menuOptions;
    }

    /**
     * @return the array representation of portfolio report menu options
     */
    public static String[] getReportOptions() {
        return reportOptions;
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
     * @return DecimalFormat for the monetary values
     */
    public static DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    /**
     * @return the DateTimeFormatter for this program, date only
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return dateFormat;
    }

    /**
     * @return the DateTimeFormatter for this program, report use
     */
    public static DateTimeFormatter getDateTimeDetailFormatter() {
        return dateFormatDetail;
    }
}
