package PropertyManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class provides some functionalities related to the interaction with the database. In the project,
 * the database consists of four files: clients.txt, expenses.txt, properties.txt, and rents.txt. Any
 * method to read/write the database file should be contained this class.
 */

public class DatabaseUtility {

    // default source file names to be read
    private static String fileClients = EnvManager.getFileClients();
    private static String fileProperties = EnvManager.getFileProperties();
    private static String fileExpenses = EnvManager.getFileExpenses();
    private static String fileRents = EnvManager.getFileRents();

    // flag to check whether if the record is saved or not
    private static boolean isSaved = true;


    /**
     * @return true if there is no unsaved data, false if there is unsaved data
     */
    public static boolean getIsSaved() {
        return isSaved;
    }

    /**
     * @param save - the flag of isSaved status: [true]: no unsaved changes, [false]: change unsaved.
     */
    public static void setIsSaved(boolean save) {
        isSaved = save;
    }


    /**
     * Read the data from the source file and instantiate a new Client object.
     * The data fields of the new object will be setted with next dataset from the Scanner parameter.
     *
     * @param input - Scanner object to read the source file
     * @return the reference of the new Client object
     */
    private static Client readClient(Scanner input) {
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
     * Read the data from the source file and instantiate a new Property object.
     * The data fields of the new object will be setted with next dataset from the Scanner parameter.
     *
     * @param input - Scanner object to read the source file
     * @return the reference of the new Property object
     */
    private static Property readProperty(Scanner input) {
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
     * Read the data from the source file and instantiate a new Expense object.
     * The data fields of the new object will be setted with next dataset from the Scanner parameter.
     *
     * @param input - Scanner object to read the source file
     * @return the reference of the new Expense object
     */
    private static Expense readExpense(Scanner input) {
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
     * Read the data from the source file and instantiate a new Rent object.
     * The data fields of the new object will be setted with next dataset from the Scanner parameter.
     *
     * @param input - Scanner object to read the source file
     * @return the reference of the new Rent object
     */
    private static Rent readRent(Scanner input) {
        // instantiate a new Rent object
        Rent newRent = new Rent();

        newRent.setPropertyID(input.nextInt());
        newRent.setRentAmount(input.nextDouble());
        newRent.setDate(input.next());

        // return the reference of the new instance of Rent
        return newRent;
    }


    /**
     * Loading the clients records from the source file. The records will be stored in the arraylist.
     * Each Client object in the arraylist represents an individual client.
     *
     * @param clients - the arraylist the clients records to be stored
     * @param kb - a Scanner instance to get a keyboard input from the user
     */
    public static void loadingClients(ArrayList<Client> clients, Scanner kb) {
        // initialize a reference variable for Scanner object
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

        // terminate the method if the user entered "exit"
        if(fileClients.equals("exit"))
            return;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Client and store them in the arraylist
        while(inputFile.hasNext()) {
            clients.add(readClient(inputFile));
        }

        // successfully loading completed
        return;
    }


    /**
     * Loading the properties records from the source file. The records will be stored in the arraylist.
     * Each Property object in the arraylist represents an individual property.
     *
     * @param properties - the arraylist the properties records to be stored
     * @param kb - a Scanner instance to get a keyboard input from the user
     */
    public static void loadingProperties(ArrayList<Property> properties, Scanner kb) {
        // initialize a reference variable for Scanner object
        Scanner inputFile = null;

        // read the properties file and instantiate Scanner objects
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

        // terminate the method if the user entered "exit"
        if(fileProperties.equals("exit"))
            return;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Property and store them in the arraylist
        while(inputFile.hasNext()) {
            properties.add(readProperty(inputFile));
        }

        // successfully loading completed
        return;
    }


    /**
     * Loading the expenses records from the source file. The records will be stored in the arraylist.
     * Each Expense object in the arraylist represents an individual expense.
     *
     * @param expenses - the arraylist the expenses records to be stored
     * @param kb - a Scanner instance to get a keyboard input from the user
     */
    public static void loadingExpenses(ArrayList<Expense> expenses, Scanner kb) {
        // initialize a reference variable for Scanner object
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

        // terminate the method if the user entered "exit"
        if(fileExpenses.equals("exit"))
            return;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Expense and store them in the arraylist
        while(inputFile.hasNext()) {
            expenses.add(readExpense(inputFile));
        }

        // successfully loading completed
        return;
    }


    /**
     * Loading the rents records from the source file. The records will be stored in the arraylist.
     * Each Rent object in the arraylist represents an individual rent collection event.
     *
     * @param rents - the arraylist the rents records to be stored
     * @param kb - a Scanner instance to get a keyboard input from the user
     */
    public static void loadingRents(ArrayList<Rent> rents, Scanner kb) {
        // initialize a reference variable for Scanner object
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

        // terminate the method if the user entered "exit"
        if(fileRents.equals("exit"))
            return;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Rent and store them in the arraylist
        while(inputFile.hasNext()) {
            rents.add(readRent(inputFile));
        }

        // successfully loading completed
        return;
    }
}