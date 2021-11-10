package PropertyManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * This class provides some functionalities related to the interaction with the database. In the project,
 * the database consists of four files: clients.txt, expenses.txt, properties.txt, and rents.txt. Any
 * method to read/write the database file should be contained this class.
 */

public class DatabaseUtility {

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
     * @param fileName - the name of the source file containing the records of clients
     * @param kb - a Scanner instance to get a keyboard input from the user
     * @return the loaded arraylist of Clients
     */
    public static ArrayList<Client> loadingClients(ArrayList<Client> clients, String fileName, Scanner kb) {
        // initialize a reference variable for Scanner object
        Scanner inputFile = null;

        // read the clients file and instantiate Scanner objects
        while(!fileName.equals("exit")) {
            try {
                File sourceFile = new File(fileName);
                inputFile = new Scanner(sourceFile);
                break; // successfully read

            } catch (FileNotFoundException exception) {
                System.out.println("Error - File not found: " + exception.getMessage());
                System.out.println("\nEnter the name of the file that is to be read."
                        + " Type \"exit\" to exit.");
                System.out.print("File Name?: ");
                fileName = kb.nextLine(); // get a new file name to be read
            }
        }

        // terminate the method if the user entered "exit"
        if(fileName.equals("exit"))
            return clients;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Client and store them in the arraylist
        while(inputFile.hasNext()) {
            clients.add(readClient(inputFile));
        }

        // successfully loading completed, return the reference of ArrayList<Client>
        return clients;
    }


    /**
     * Loading the properties records from the source file. The records will be stored in the arraylist.
     * Each Property object in the arraylist represents an individual property.
     *
     * @param properties - the arraylist the properties records to be stored
     * @param fileName - the name of the source file containing the records of properties
     * @param kb - a Scanner instance to get a keyboard input from the user
     * @return the loaded arraylist of Properties
     */
    public static ArrayList<Property> loadingProperties(ArrayList<Property> properties, String fileName, Scanner kb) {
        // initialize a reference variable for Scanner object
        Scanner inputFile = null;

        // read the properties file and instantiate Scanner objects
        while(!fileName.equals("exit")) {
            try {
                File sourceFile = new File(fileName);
                inputFile = new Scanner(sourceFile);
                break; // successfully read

            } catch (FileNotFoundException exception) {
                System.out.println("Error - File not found: " + exception.getMessage());
                System.out.println("\nEnter the name of the file that is to be read."
                        + " Type \"exit\" to exit.");
                System.out.print("File Name?: ");
                fileName = kb.nextLine(); // get a new file name to be read
            }
        }

        // terminate the method if the user entered "exit"
        if(fileName.equals("exit"))
            return properties;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Property and store them in the arraylist
        while(inputFile.hasNext()) {
            properties.add(readProperty(inputFile));
        }

        // successfully loading completed, return the reference of ArrayList<Property>
        return properties;
    }


    /**
     * Loading the expenses records from the source file. The records will be stored in the arraylist.
     * Each Expense object in the arraylist represents an individual expense.
     *
     * @param expenses - the arraylist the expenses records to be stored
     * @param fileName - the name of the source file containing the records of expenses
     * @param kb - a Scanner instance to get a keyboard input from the user
     * @return the loaded arraylist of Expenses
     */
    public static ArrayList<Expense> loadingExpenses(ArrayList<Expense> expenses, String fileName, Scanner kb) {
        // initialize a reference variable for Scanner object
        Scanner inputFile = null;

        // read the expenses file and instantiate Scanner objects
        while(!fileName.equals("exit")) {
            try {
                File sourceFile = new File(fileName);
                inputFile = new Scanner(sourceFile);
                break; // successfully read

            } catch (FileNotFoundException exception) {
                System.out.println("Error - File not found: " + exception.getMessage());
                System.out.println("\nEnter the name of the file that is to be read."
                        + " Type \"exit\" to exit.");
                System.out.print("File Name?: ");
                fileName = kb.nextLine(); // get a new file name to be read
            }
        }

        // terminate the method if the user entered "exit"
        if(fileName.equals("exit"))
            return expenses;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Expense and store them in the arraylist
        while(inputFile.hasNext()) {
            expenses.add(readExpense(inputFile));
        }

        // successfully loading completed, return the reference of ArrayList<Expense>
        return expenses;
    }


    /**
     * Loading the rents records from the source file. The records will be stored in the arraylist.
     * Each Rent object in the arraylist represents an individual rent collection event.
     *
     * @param rents - the arraylist the rents records to be stored
     * @param fileName - the name of the source file containing the records of rents
     * @param kb - a Scanner instance to get a keyboard input from the user
     * @return the loaded arraylist of Rents
     */
    public static ArrayList<Rent> loadingRents(ArrayList<Rent> rents, String fileName, Scanner kb) {
        // initialize a reference variable for Scanner object
        Scanner inputFile = null;

        // read the rents file and instantiate Scanner objects
        while(!fileName.equals("exit")) {
            try {
                File sourceFile = new File(fileName);
                inputFile = new Scanner(sourceFile);
                break; // successfully read

            } catch (FileNotFoundException exception) {
                System.out.println("Error - File not found: " + exception.getMessage());
                System.out.println("\nEnter the name of the file that is to be read."
                        + " Type \"exit\" to exit.");
                System.out.print("File Name?: ");
                fileName = kb.nextLine(); // get a new file name to be read
            }
        }

        // terminate the method if the user entered "exit"
        if(fileName.equals("exit"))
            return rents;

        // set delimiters for the Scanner objects as ",", "\n", and "\r\n"
        inputFile.useDelimiter(",|\n|\r\n");

        // instantiate objects of Rent and store them in the arraylist
        while(inputFile.hasNext()) {
            rents.add(readRent(inputFile));
        }

        // successfully loading completed, return the reference of ArrayList<Rent>
        return rents;
    }


    /**
     * Search the clients that their name is containing specific search keyword.
     * The search would return all and any clients whose name included the search keyword.
     *
     * @param clients - the arraylist of all clients
     * @param searchRaw - search keyword
     * @return the arraylist of clients matched to search
     */
    public static ArrayList<Client> searchClientsByName
    (ArrayList<Client> clients, String searchRaw) {

        // convert all characters in the search keyword to upper case
        String search = searchRaw.toUpperCase();

        // predicates for the filter, check whether if the name contains the search keyword or not
        Predicate<Client> filterFirstName = c -> c.getFirstName().toUpperCase().contains(search);
        Predicate<Client> filterLastName = c -> c.getLastName().toUpperCase().contains(search);

        // search all match case and store them in the arraylist
        ArrayList<Client> searchResults = new ArrayList<>(clients.stream().
                filter(filterFirstName.or(filterLastName))
                .collect(Collectors.toList()));

        return searchResults;
    }


    /**
     * Search the properties that their address is containing specific search keyword.
     * The search would return all and any properties whose address included the search keyword.
     *
     * @param properties - the arraylist of all properties
     * @param searchRaw - search keyword
     * @return the arraylist of properties matched to search
     */
    public static ArrayList<Property> searchPropertiesByAddress
    (ArrayList<Property> properties, String searchRaw) {

        // convert all characters in the search keyword to upper case
        String search = searchRaw.toUpperCase();

        // predicates for the filter, check whether if the address contains the search keyword or not
        Predicate<Property> filterStreet = p -> p.getStreet().toUpperCase().contains(search);
        Predicate<Property> filterSuburb = p -> p.getSuburb().toUpperCase().contains(search);
        Predicate<Property> filterState = p -> p.getState().toUpperCase().contains(search);
        Predicate<Property> filterPostcode = p -> p.getPostcode().contains(search);

        // search all match case and store them in the arraylist
        ArrayList<Property> searchResults = new ArrayList<>(properties.stream().
                filter(filterStreet.or(filterSuburb).or(filterState).or(filterPostcode))
                .collect(Collectors.toList()));

        return searchResults;
    }


    /**
     * Search the properties that their postcode is containing specific search keyword.
     * The search would return all and any properties whose postcode included the search keyword.
     *
     * @param properties - the arraylist of all properties
     * @param searchRaw - search keyword
     * @return the arraylist of properties matched to search
     */
    public static ArrayList<Property> searchPropertiesByPostcode
    (ArrayList<Property> properties, String searchRaw) {

        // convert all characters in the search keyword to upper case
        String search = searchRaw.toUpperCase();

        // predicate for the filter, check whether if the postcode contains the search keyword or not
        Predicate<Property> filterPostcode = p -> p.getPostcode().contains(search);

        // search all match case and store them in the arraylist
        ArrayList<Property> searchResults = new ArrayList<>(properties.stream().
                filter(filterPostcode)
                .collect(Collectors.toList()));

        return searchResults;
    }
}