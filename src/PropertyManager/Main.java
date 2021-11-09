package PropertyManager;

import java.io.File;
import java.io.FileNotFoundException;
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
        int option = -1;

        option = loadingClients();

        if (option != 0)
            option = loadingProperties();
        if (option != 0)
            option = loadingExpenses();
        if (option != 0)
            option = loadingRents();

    }


    /**
     * Instantiate a new Client object.
     * The data fields of the new object will be setted with next dataset from the Scanner parameter.
     *
     * @param input - Scanner object to read the source file
     * @return the reference of the new Client object
     */
    static Client instantiateClient(Scanner input) {
        // instantiate a new Client object
        Client newClient = new Client();

        newClient.setID(input.nextInt());
        newClient.setFullName(input.next());
        newClient.setStreet(input.next());
        newClient.setSuburb(input.next());
        newClient.setState(input.next());
        newClient.setPostcode(input.next());

        // return the reference of the new instance of Client
        return newClient;
    }

    /**
     * Instantiate a new Property object.
     * The data fields of the new object will be setted with next dataset from the Scanner parameter.
     *
     * @param input - Scanner object to read the source file
     * @return the reference of the new Property object
     */
    static Property instantiateProperty(Scanner input) {
        // instantiate a new Property object
        Property newProperty = new Property();

        newProperty.setID(input.nextInt());
        newProperty.setStreet(input.next());
        newProperty.setSuburb(input.next());
        newProperty.setState(input.next());
        newProperty.setPostcode(input.next());
        newProperty.setRentWeekly(input.nextDouble());
        newProperty.setManagementRate(input.nextDouble());
        newProperty.setClientID(input.nextInt());

        // return the reference of the new instance of Property
        return newProperty;
    }

    /**
     * Instantiate a new Expense object.
     * The data fields of the new object will be setted with next dataset from the Scanner parameter.
     *
     * @param input - Scanner object to read the source file
     * @return the reference of the new Expense object
     */
    static Expense instantiateExpense(Scanner input) {
        // instantiate a new Expense object
        Expense newExpense = new Expense();

        newExpense.setPropertyID(input.nextInt());
        newExpense.setDescription(input.next());
        newExpense.setCost(input.nextDouble());
        newExpense.setDate(input.next());

        // return the reference of the new instance of Expense
        return newExpense;
    }

    /**
     * Instantiate a new Rent object.
     * The data fields of the new object will be setted with next dataset from the Scanner parameter.
     *
     * @param input - Scanner object to read the source file
     * @return the reference of the new Rent object
     */
    static Rent instantiateRent(Scanner input) {
        // instantiate a new Rent object
        Rent newRent = new Rent();

        newRent.setPropertyID(input.nextInt());
        newRent.setRentAmount(input.nextDouble());
        newRent.setDate(input.next());

        // return the reference of the new instance of Rent
        return newRent;
    }


    /**
     * Loading the clients records from the source file. The records will be stored in the global
     * arraylist "clients". Each Client object in the arraylist represents an individual client.
     *
     * @return the flag of loading status. "-1": successful, "0": failed
     */
    static int loadingClients() {
        // initialize Scanner object references
        Scanner inputFile = null;

        // read the clients file and instantiate Scanner objects
        while(!fileClients.equals("exit")) {
            try {
                File sourceFile = new File(fileClients);
                inputFile = new Scanner(sourceFile);
                break; // successfully read

            } catch (FileNotFoundException exception) {
                System.out.println("Error - File not found: " + exception.getMessage());
                System.out.println("\nEnter the name of the file that is to be read."
                        + " Type \"exit\" to exit.");
                System.out.print("File Name?: ");
                fileClients = kb.nextLine(); // get a new file name to be read
            }
        }

        // terminate the program if the user entered "exit"
        if(fileClients.equals("exit"))
            return 0;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Client and store them in the arraylist
        while(inputFile.hasNext()) {
            clients.add(instantiateClient(inputFile));
        }

        // successfully loading completed
        return -1;
    }

    /**
     * Loading the properties records from the source file. The records will be stored in the global
     * arraylist "properties". Each Property object in the arraylist represents a single property.
     *
     * @return the flag of loading status. "-1": successful, "0": failed
     */
    static int loadingProperties() {
        // initialize Scanner object references
        Scanner inputFile = null;

        // read the clients file and instantiate Scanner objects
        while(!fileProperties.equals("exit")) {
            try {
                File sourceFile = new File(fileProperties);
                inputFile = new Scanner(sourceFile);
                break; // successfully read

            } catch (FileNotFoundException exception) {
                System.out.println("Error - File not found: " + exception.getMessage());
                System.out.println("\nEnter the name of the file that is to be read."
                        + " Type \"exit\" to exit.");
                System.out.print("File Name?: ");
                fileProperties = kb.nextLine(); // get a new file name to be read
            }
        }

        // terminate the program if the user entered "exit"
        if(fileProperties.equals("exit"))
            return 0;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Property and store them in the arraylist
        while(inputFile.hasNext()) {
            properties.add(instantiateProperty(inputFile));
        }

        // successfully loading completed
        return -1;
    }

    /**
     * Loading the expenses records from the source file. The records will be stored in the global
     * arraylist "expenses". Each Expense object in the arraylist represents an individual expense.
     *
     * @return the flag of loading status. "-1": successful, "0": failed
     */
    static int loadingExpenses() {
        // initialize Scanner object references
        Scanner inputFile = null;

        // read the expenses file and instantiate Scanner objects
        while(!fileExpenses.equals("exit")) {
            try {
                File sourceFile = new File(fileExpenses);
                inputFile = new Scanner(sourceFile);
                break; // successfully read

            } catch (FileNotFoundException exception) {
                System.out.println("Error - File not found: " + exception.getMessage());
                System.out.println("\nEnter the name of the file that is to be read."
                        + " Type \"exit\" to exit.");
                System.out.print("File Name?: ");
                fileExpenses = kb.nextLine(); // get a new file name to be read
            }
        }

        // terminate the program if the user entered "exit"
        if(fileExpenses.equals("exit"))
            return 0;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Expense and store them in the arraylist
        while(inputFile.hasNext()) {
            expenses.add(instantiateExpense(inputFile));
        }

        // successfully loading completed
        return -1;
    }

    /**
     * Loading the rents records from the source file. The records will be stored in the global
     * arraylist "rents". Each Rent object in the arraylist represents a single rent collection.
     *
     * @return the flag of loading status. "-1": successful, "0": failed
     */
    static int loadingRents() {
        // initialize Scanner object references
        Scanner inputFile = null;

        // read the rents file and instantiate Scanner objects
        while(!fileRents.equals("exit")) {
            try {
                File sourceFile = new File(fileRents);
                inputFile = new Scanner(sourceFile);
                break; // successfully read

            } catch (FileNotFoundException exception) {
                System.out.println("Error - File not found: " + exception.getMessage());
                System.out.println("\nEnter the name of the file that is to be read."
                        + " Type \"exit\" to exit.");
                System.out.print("File Name?: ");
                fileRents = kb.nextLine(); // get a new file name to be read
            }
        }

        // terminate the program if the user entered "exit"
        if(fileRents.equals("exit"))
            return 0;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Rent and store them in the arraylist
        while(inputFile.hasNext()) {
            rents.add(instantiateRent(inputFile));
        }

        // successfully loading completed
        return -1;
    }
}
