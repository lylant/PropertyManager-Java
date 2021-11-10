package PropertyManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    // Scanner object for the keyboard input
    private static Scanner kb = new Scanner(System.in);

    // set some special numbers for the menu selection
    private static int optionMin = EnvManager.getMenuOptionMin();   // min digit of menu list
    private static int optionExit = EnvManager.getMenuOptionExit(); // digit of exit option


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
     */
    public static void displayMenu(String[] menu) {
        System.out.println("\nWelcome, administrator. Please select the option below: \n");
        for (int i = 0; i < (menu.length - 1); i++) {
            System.out.println( "\t" + (i + optionMin) + ". " + menu[i]);
        }
        System.out.println("\t" + optionExit + ". " + menu[menu.length-1]);
    }


    /**
     * Obtains and validates digit option selections
     *
     * @param menu - an array representation of menu options
     * @param kb - a Scanner instance to get a keyboard input from the user
     * @return the users selection from the available options
     */
    public static int getMenuSelect(String[] menu, Scanner kb) {

        int option = optionMin; // the user input for the menu selection
        int optionMax = menu.length + optionMin - 2; // a maximum digit number of the option list

        do {
            // invalid input, print the error message
            if (option < optionMin || option > optionMax)
                System.out.println("\n[!] Invalid option. Please select a option with a digit number.");
            System.out.print("\nEnter the option: ");

            try {
                option = kb.nextInt();
                kb.nextLine(); // consume "\n"
            } catch (InputMismatchException exception) {
                option = -1; // replace the option variable with no case match value
                kb.nextLine(); // consume "\n"
            }
        } while ((option < optionMin || option > optionMax) && option != optionExit);

        // received the valid selection, convert to numbers as 1, 2, 3... to fit the switch statement
        // do not convert if the input is match as exiting the program
        if (option != optionExit && (option - optionMin + 1) != optionExit)
            option -= (optionMin - 1);

        return option;
    }
}
