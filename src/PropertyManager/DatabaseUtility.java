package PropertyManager;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class provides some functionalities related to the interaction with the database. In the project,
 * the database consists of four files: clients.txt, expenses.txt, properties.txt, and rents.txt. Any
 * method to read/write the database file should be contained this class.
 */

public class DatabaseUtility {

    // decimal format for the monetary value
    private static DecimalFormat df = EnvManager.getDecimalFormat();

    // default source file names to be read
    private static String fileClients = EnvManager.getFileClients();
    private static String fileProperties = EnvManager.getFileProperties();
    private static String fileExpenses = EnvManager.getFileExpenses();
    private static String fileRents = EnvManager.getFileRents();

    // arraylists to store new records of expense/rent, will be used for the save
    private static ArrayList<Expense> newExpenses = new ArrayList<>();
    private static ArrayList<Rent> newRents = new ArrayList<>();

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
     * Store a new record to the Expenses arraylist which is separated from the main arraylist of Expense.
     *
     * @param newExpense - a new Expense instance to be stored
     */
    public static void storeNewExpense(Expense newExpense) {
        newExpenses.add(newExpense);
    }

    /**
     * Store a new record to the Rents arraylist which is separated from the main arraylist of Rent.
     *
     * @param newRent - a new Rent instance to be stored
     */
    public static void storeNewRent(Rent newRent) {
        newRents.add(newRent);
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

    
    /**
     * Write a data of specific Expense instance in a specific file. This method will write 1 row of data.
     * Each data field will be delimited by comma.
     *
     * @param expense - the Expense instance to be written
     * @param outFile - the file containing data of expenses
     */
    private static void writeFileExpense(Expense expense, PrintWriter outFile) {
        outFile.print(expense.getPropertyID() + ",");
        outFile.print(expense.getDescription() + ",");
        outFile.print(df.format(expense.getCost()) + ",");
        outFile.println(expense.getDate());
    }


    /**
     * Write a data of specific Rent instance in a specific file. This method will write 1 row of data.
     * Each data field will be delimitted by comma.
     *
     * @param rent - the Rent instance to be written
     * @param outFile - the file containing data of rents
     */
    private static void writeFileRent(Rent rent, PrintWriter outFile) {
        outFile.print(rent.getPropertyID() + ",");
        outFile.print(df.format(rent.getRentAmount()) + ",");
        outFile.println(rent.getDate());
    }


    /**
     * Save all rent and expense information entered by the user via the other menu items, to the proper
     * files respectively. Pre-existing data in the files will be preserved. 
     */
    public static void saveChanges() {
        // initialize the FileWriter and PrintWriter variables
        FileWriter fwExpenses = null;
        PrintWriter pwExpenses = null;
        FileWriter fwRents = null;
        PrintWriter pwRents = null;

        // no changes found, terminate the method
        if(isSaved) {
            System.out.println("\n [!] No data changes found since the last save.");
            return;
        }

        // perform the save
        try {
            // save expenses data
            fwExpenses = new FileWriter(fileExpenses, true);
            pwExpenses = new PrintWriter(fwExpenses);

            for (int i = 0; i < newExpenses.size(); i++) {
                writeFileExpense(newExpenses.get(i), pwExpenses);
            }

            // save rents data
            fwRents = new FileWriter(fileRents, true);
            pwRents = new PrintWriter(fwRents);

            for (int i = 0; i < newRents.size(); i++) {
                writeFileRent(newRents.get(i), pwRents);
            }

            // save process completed
            pwExpenses.close();
            pwRents.close();
            newExpenses.clear();
            newRents.clear();
            isSaved = true;

            System.out.println("\n [*] Save successful.");
            return;

        } catch (IOException exception) {
            System.out.println(" [!] Error - I/O exception: " + exception.getMessage());
            System.out.println(" [!] Your save has failed. Please check if the source files are corrupted.");
            return;
        }
    }
}