package PropertyManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    // Scanner object for the keyboard input
    private static Scanner kb = new Scanner(System.in);

    // set some special numbers for the menu selection
    private static int optionMin = 1;  // a minimum digit number of the option list
    //private static int optionExit = 0; // a digit number to choose the exit program option


    /**
     * Display the title artwork
     */
    public static void displayTitle() {
        System.out.println("    ++++++++++++++++++++++++");
        System.out.println("    +++ PROPERTY MANAGER +++");
        System.out.println("    ++++++++++++++++++++++++");
    }


    /**
     * Display the available menu options to the console from the menu parameter.
     * The last item of the parameter should be "Exit the Program" option.
     *
     * @param menu - an array representation of menu options
     * @param optionExit - the digit number to choose the exit program option
     */
    public static void displayMenu(String[] menu, int optionExit) {
        System.out.println("\nWelcome, administrator. Please select the option below: \n");
        for (int i = 0; i < (menu.length - 1); i++) {
            System.out.println( "\t" + (i + optionMin) + ". " + menu[i]);
        }
        System.out.println("\t" + optionExit + ". " + menu[menu.length-1]);
        System.out.print("\nEnter the option: ");
    }


    /**
     * Obtains and validates digit option selections
     *
     * @param menu - an array representation of menu options
     * @param optionExit - the digit number to choose the exit program option
     * @return the users selection from the available options
     */
    public static int getMenuSelect(String[] menu, int optionExit) {

        int option = -1; // user input: a default value should not be one of the match case of options
        int optionMax = menu.length + optionMin - 2; // a maximum digit number of the option list

        do {
            try {
                option = kb.nextInt();
                kb.nextLine(); // consume "\n"
            } catch (InputMismatchException exception) {
                System.out.println("Error - Datatype mismatch: " + exception.getMessage());
                kb.nextLine(); // consume "\n"
            }
        } while ((option < optionMin || option > optionMax) && option != optionExit);

        return option;
    }
}
