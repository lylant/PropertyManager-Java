package PropertyManager;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * This class provides some displaying the information functionalities. Displaying the table of
 * info, a detail of specific class, on-screen reports, etc. methods will be modularised and be
 * placed in this class.
 */

public class ViewUtility {
    
    // decimal format for the monetary value;
    private static DecimalFormat df = EnvManager.getDecimalFormat();


    /**
     * Draw a border line with hyphen.
     *
     * @param length - the number of hyphen will be printed
     */
    public static void drawBorder(int length) {
        for (int i=0; i < length; i++)
            System.out.print("-");
        System.out.println();
    }


    /**
     * Display the portfolio report for the clients. The report shows all property's data each client
     * has, such as rent, expenses, fee rate, fees, and net value. If the client has no record of 
     * property or rent/expense, the method will display information message for that. The report's
     * output will be sorted by client's last name in ascending order.
     *
     * @param clients - the arraylist of clients of interested
     * @param properties - the arraylist of all properties
     * @param expenses - the arraylist of all expenses
     * @param rents - the arraylist of all rents
     */
    private static void displayReportClient(ArrayList<Client> clients, ArrayList<Property> properties
            , ArrayList<Expense> expenses, ArrayList<Rent> rents) {

        /*
         *  initialize local variables:
         */

        // table formatters
        int borderLength = 114; // table border length
        String tableFormatHeader = "%-32s%3s%-50s"; // header formatter
        String tableFormatBody = "%2s%52s%2s%10s%2s%10s%2s%9s%2s%9s%2s%10s%2s"; // body formatter

        // variables for evaluating the sum values
        double rentTotal;       // a cumulative value of rents of each property
        double expensesTotal;   // a cumulative value of expenses of each property
        double feeTotal;        // a cumulative value of fee of each property

        // current datetime
        DateTimeFormatter dateFormat = EnvManager.getDateTimeDetailFormatter(); // formatter
        String datetime = LocalDateTime.now().format(dateFormat); // String containing of the current datetime

        // initial sorting
        SortUtility.sortClientByLastName(clients);
        SortUtility.sortPropertyByClientID(properties);
        ArrayList<Expense> sortedExpenses = SortUtility.sortCopiedExpenseByPropertyID(expenses);
        ArrayList<Rent> sortedRents = SortUtility.sortCopiedRentsByPropertyID(rents);


        // report title
        drawBorder(borderLength);
        System.out.println(" [*] PORTFOLIO REPORT");
        System.out.println("Report Generated: " + datetime);

        // generate the report of each clients
        for (int i = 0; i < clients.size(); i++) {
            // initialize the cumulative value
            rentTotal = 0;
            expensesTotal = 0;
            feeTotal = 0;

            // table title
            System.out.format(tableFormatHeader,
                    ("\n [*] Client: " + clients.get(i).getFullName()), " / ", clients.get(i).getAddress());
            System.out.println();

            // no property records, skip the contents
            if ( !Validator.validateOwnerHasProperty(properties, clients.get(i).getID()) ) {
                drawBorder(borderLength);
                System.out.println(" [!] This client possesses no property.");
                drawBorder(borderLength);
                continue;
            }

            // no rent/expense records, skip the contents
            if ( !Validator.validateOwnerHasRentOrExpense
                    (properties, sortedExpenses, sortedRents, clients.get(i).getID()) ) {
                drawBorder(borderLength);
                System.out.println(" [!] This client has no rental/expense records yet.");
                drawBorder(borderLength);
                continue;
            }

            // table border
            drawBorder(borderLength);
            // table header
            System.out.format(tableFormatBody, "| ", "Property", " |", "Rent", " |", "Expenses"
                    , " |", "Fee Rate", " |", "Fees", " |", "Net", " |");
            System.out.println();
            drawBorder(borderLength);

            // table body 1: find all properties from the client and print the detail of each property
            for (int j=0; j < properties.size(); j++) {
                if (properties.get(j).getClientID() == clients.get(i).getID()) {
                    // accumulate the sum value
                    rentTotal += properties.get(j).getTotalRent(sortedRents);
                    expensesTotal += properties.get(j).getTotalExpenses(sortedExpenses);
                    feeTotal += properties.get(j).getManagementFee(sortedRents);

                    System.out.format(tableFormatBody, "| ", properties.get(j).getAddress()
                            , " |", df.format(properties.get(j).getTotalRent(sortedRents))
                            , " |", df.format(properties.get(j).getTotalExpenses(sortedExpenses))
                            , " |", df.format(properties.get(j).getManagementRate())
                            , " |", df.format(properties.get(j).getManagementFee(sortedRents))
                            , " |", df.format(properties.get(j).getNetBalance(sortedRents, sortedExpenses))
                            , " |");
                    System.out.println();
                }
                if (properties.get(j).getClientID() > clients.get(i).getID()) // no further record
                    break;
            }

            // table body 2: sum values of all properties
            drawBorder(borderLength);
            System.out.format(tableFormatBody, "| ", "TOTAL"
                    , " |", df.format(rentTotal)
                    , " |", df.format(expensesTotal)
                    , " |", ""
                    , " |", df.format(feeTotal)
                    , " |", df.format(rentTotal - expensesTotal - feeTotal)
                    , " |");
            System.out.println();
            drawBorder(borderLength);
        }
    }


    /**
     * Display the portfolio report for the postcode. The report shows all property's data in the
     * postcode area, such as rent, expenses, fee rate, fees, and net value. This method will not
     * consider the case there is no property in the postcode since the search filters that case
     * already. The report's output will be sorted by propertyID in ascending order.
     *
     * @param properties - the arraylist of all properties in the postcode area
     * @param expenses - the arraylist of all expenses
     * @param rents - the arraylist of all rents
     */
    private static void displayReportPostcode(ArrayList<Property> properties
            , ArrayList<Expense> expenses, ArrayList<Rent> rents) {

        /*
         *  initialize local variables:
         */

        // table formatters
        int borderLength = 114; // table border length
        String tableFormatBody = "%2s%52s%2s%10s%2s%10s%2s%9s%2s%9s%2s%10s%2s"; // body formatter

        // variables for evaluating the sum values
        double rentTotal = 0;     // a cumulative value of rents of each property
        double expensesTotal = 0; // a cumulative value of expenses of each property
        double feeTotal = 0;      // a cumulative value of fee of each property

        // current datetime
        DateTimeFormatter dateFormat = EnvManager.getDateTimeDetailFormatter(); // formatter
        String datetime = LocalDateTime.now().format(dateFormat); // String containing of the current datetime

        // initial sorting
        SortUtility.sortPropertyByID(properties);
        ArrayList<Expense> sortedExpenses = SortUtility.sortCopiedExpenseByPropertyID(expenses);
        ArrayList<Rent> sortedRents = SortUtility.sortCopiedRentsByPropertyID(rents);


        // report title
        drawBorder(borderLength);
        System.out.println(" [*] PORTFOLIO REPORT");
        System.out.println("Report Generated: " + datetime);

        // table title
        System.out.println("\n [*] Postcode: " + properties.get(0).getPostcode());

        // table header
        drawBorder(borderLength);
        System.out.format(tableFormatBody, "| ", "Property", " |", "Rent", " |", "Expenses"
                , " |", "Fee Rate", " |", "Fees", " |", "Net", " |");
        System.out.println();
        drawBorder(borderLength);

        // table body 1: print the detail of each property in the postcode area
        for (int i = 0; i < properties.size(); i++) {
            rentTotal += properties.get(i).getTotalRent(sortedRents);
            expensesTotal += properties.get(i).getTotalExpenses(sortedExpenses);
            feeTotal += properties.get(i).getManagementFee(sortedRents);

            System.out.format(tableFormatBody, "| ", properties.get(i).getAddress()
                    , " |", df.format(properties.get(i).getTotalRent(sortedRents))
                    , " |", df.format(properties.get(i).getTotalExpenses(sortedExpenses))
                    , " |", df.format(properties.get(i).getManagementRate())
                    , " |", df.format(properties.get(i).getManagementFee(sortedRents))
                    , " |", df.format(properties.get(i).getNetBalance(sortedRents, sortedExpenses))
                    , " |");
            System.out.println();
        }

        // table body 2: sum values of all properties
        drawBorder(borderLength);
        System.out.format(tableFormatBody, "| ", "TOTAL"
                , " |", df.format(rentTotal)
                , " |", df.format(expensesTotal)
                , " |", ""
                , " |", df.format(feeTotal)
                , " |", df.format(rentTotal - expensesTotal - feeTotal)
                , " |");
        System.out.println();
        drawBorder(borderLength);
    }
    

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
        drawBorder(58);
        // info box body
        System.out.println(property);
        // info box border
        drawBorder(58);
    }


    /**
     * Display the detail of a specific rent collection event
     *
     * @param rent - the rent collection event to be displayed
     */
    public static void displayRentDetail(Rent rent) {
        int borderLength = 66; // table border length
        System.out.println("\n [*] The Summary of the Rent Collection Transaction");
        drawBorder(borderLength);
        System.out.println(rent);
        drawBorder(borderLength);
    }


    /**
     * Display the detail of a specific expense event
     *
     * @param expense - the expense event to be displayed
     */
    public static void displayExpenseDetail(Expense expense) {
        int borderLength = 68; // table border length
        System.out.println("\n [*] The Summary of the Expense Event");
        drawBorder(borderLength);
        System.out.println(expense);
        drawBorder(borderLength);
    }


    /**
     * Display the search result of properties.
     *
     * @param search - the arraylist of properties matched to search
     */
    public static void displaySearchResult(ArrayList<Property> search) {
        // table formatters
        String formatHeader = "%-2s%-4s%-2s%-52s%-2s%-22s%-2s";
        String formatBody = "%-2s%-4d%-2s%-52s%-2s%-22s%-2s";
        int borderLength = 85;

        // the hashmap of clients to convert clientID to their name
        HashMap<Integer, Client> clientsHashMap = HashMapContainer.getClientsHashMap();


        // table title
        System.out.println("\n [*] Search Results");
        // table header
        drawBorder(borderLength);
        System.out.format(formatHeader, "| ", "ID", "| ", "Address", "| ", "Owner", "|");
        System.out.println();
        drawBorder(borderLength);
        // table body
        for (int i=0; i < search.size(); i++) {
            System.out.format(formatBody, "| ", search.get(i).getID(), "| "
                    , search.get(i).getAddress(), "|"
                    , clientsHashMap.get(search.get(i).getClientID()).getFullName(), "| ");
            System.out.println();
        }
        drawBorder(borderLength);
    }
    

    /**
     * Display a portfolio report for a specific client. The user can designate the client(s) by
     * entering the client's name. The search would return any and all clients whose name contained
     * the search keyword and produce a portfolio report for that/those client(s).
     *
     * @param clients - the arraylist containing all Client instances
     * @param properties - the arraylist containing all Property instances
     * @param expenses - the arraylist containing all Expense instances
     * @param rents - the arraylist containing all Rent instances
     * @param kb - the Scanner instance to get an user input via a keyboard
     */
    public static void displayReportSpecificClient(ArrayList<Client> clients, ArrayList<Property> properties
            , ArrayList<Expense> expenses, ArrayList<Rent> rents, Scanner kb) {

        // the arraylist to store the search results 
        ArrayList<Client> searchResults = null;

        // display the menu welcome message
        MenuUtility.displayReportSpecificClient();

        // search the client
        System.out.println("\nYou selected to generate a portfolio report for a specific client.");
        System.out.println("You can find the client by searching the client's name.");
        searchResults = SearchUtility.searchClientsByName(clients, kb);

        // generate the report
        displayReportClient(searchResults, properties, expenses, rents);

        System.out.print("\n (Press ENTER key to return to main menu)");
        kb.nextLine();
        return;
    }


    /**
     * Display a portfolio report for all clients.
     *
     * @param clients - the arraylist containing all Client instances
     * @param properties - the arraylist containing all Property instances
     * @param expenses - the arraylist containing all Expense instances
     * @param rents - the arraylist containing all Rent instances
     * @param kb - the Scanner instance to get an user input via a keyboard
     */
    public static void displayReportAllClient(ArrayList<Client> clients, ArrayList<Property> properties
            , ArrayList<Expense> expenses, ArrayList<Rent> rents, Scanner kb) {

        // generate the report
        System.out.println();
        displayReportClient(clients, properties, expenses, rents);

        System.out.print("\n (Press ENTER key to return to main menu)");
        kb.nextLine();
        return;
    }


    /**
     * Display a portfolio report for a specified postcode. The user can designate the postcode.
     * The method will produce the report for all properties that are located in the entered postcode.
     *
     * @param properties - the arraylist containing all Property instances
     * @param expenses - the arraylist containing all Expense instances
     * @param rents - the arraylist containing all Rent instances
     * @param kb - the Scanner instance to get an user input via a keyboard
     */
    public static void displayReportSpecificPostcode(ArrayList<Property> properties
            , ArrayList<Expense> expenses, ArrayList<Rent> rents, Scanner kb) {

        // the arraylist to store the search results 
        ArrayList<Property> searchResults = null;

        // display the menu welcome message
        MenuUtility.displayReportSpecificPostcode();

        // search the client
        System.out.println("\nYou selected to generate a portfolio report for a specified postcode.");
        System.out.println("The report will show all properties that are located in the postcode area.");
        searchResults = SearchUtility.searchPropertiesByPostcode(properties, kb);

        // generate the report
        displayReportPostcode(searchResults, expenses, rents);

        System.out.print("\n (Press ENTER key to return to main menu)");
        kb.nextLine();
        return;
    }
}