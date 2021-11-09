package PropertyManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
     *  GLOBAL VARIABLES
     */

    // Scanner object for the keyboard input
    static Scanner kb = new Scanner(System.in);

    // default source file names to be read
    static String fileClients = "clients.txt";
    static String fileProperties = "properties.txt";
    static String fileExpenses = "expenses.txt";
    static String fileRents = "rents.txt";

    // instantiate arraylist objects to bind references of each class
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Property> properties = new ArrayList<>();
    static ArrayList<Expense> expenses = new ArrayList<>();
    static ArrayList<Rent> rents = new ArrayList<>();


    public static void main(String[] args) {

        // loading process
        clients = DatabaseUtility.loadingClients(clients, fileClients);
        if(clients.size() != 0)
            properties = DatabaseUtility.loadingProperties(properties, fileProperties);
        if(properties.size() != 0)
            expenses = DatabaseUtility.loadingExpenses(expenses, fileExpenses);
        if(expenses.size() != 0)
            rents = DatabaseUtility.loadingRents(rents, fileRents);

    }
}
