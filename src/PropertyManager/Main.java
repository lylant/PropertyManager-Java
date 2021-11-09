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
    }


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
}
