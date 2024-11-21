package usedcardealership.interaction;

import java.util.*;

public class Prompter {
    private static Scanner reader = new Scanner(System.in);

    /**
     * Closes the reader
     * To be used at end of program.
     */
    public static void close() {
        reader.close();
    }

    /**
     * General prompt to get user input
     * 
     * @return String - user input
     */
    private static String prompt() {
        System.out.print("> ");
        return reader.nextLine();
    }

    /**
     * Prompts users with a list of options and validates input
     * 
     * @param question the question/menu options
     * @param optionCap the total amount of options (that are not 0)
     * @return the number the user has chosen
     */
    public static int promptOption(String question, int optionCap) {
        int value = -1;
        boolean invalidValue = true;

        if (!question.equals("")) {
            System.out.println(question);
        }

        while (invalidValue) {
            String input = prompt();
            try {
                if (!input.equals("")) {
                    int pendingValue = Integer.parseInt(input);
                    if ((pendingValue >= 0 && pendingValue <= optionCap) || optionCap == 0) {
                        value = pendingValue;
                        invalidValue = false;
                    } else {
                        System.out.println("Invalid input! Please choose a valid option.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Illegal input!");
            }
        }
        return value;
    }

    /**
     * Gets an int and only an int from the user
     * 
     * @return the int the user chose
     */
    public static int promptInt() {
        return promptOption("", 0);
    }

    /**
     * Prompts user to input vehicle ID
     * 
     * @return the vehicle ID the user chose
     */
    public static int promptVehicleId() {
        System.out.println("\nSelect a Vehicle ID:");
        return promptInt();
    }

    /**
     * Prompts user to input a vehicle Make
     * 
     * @return the String the user input
     */
    public static String promptVehicleMake() {
        System.out.println("\nEnter Make: (Honda, Hyundai, Ford, etc)");
        return promptString();
    }

    /**
     * Prompts user to input a vehicle Color
     * 
     * @return the String the user input
     */
    public static String promptVehicleColor() {
        System.out.println("\nEnter Color: (Red, Blue, Green)");
        return promptString();
    }

    /**
     * Makes the user press Enter to continue
     */
    public static void promptEnter() {
        System.out.println("\nPress ENTER to continue:");
        reader.nextLine();
    }

    /**
     * Gets the user's input, null if empty
     * 
     * @return User input
     */
    public static String promptString() {
        String input = prompt();
        if (removeSpace(input) == null) {
            return null;
        }
        return input;
    }


    /**
     * Helper method to see if strings spaces and removes them to return null
     * 
     * @return the input, or null if input is spaces
     */
    private static String removeSpace(String input) {
        if (input.replaceAll("\\s", "").equals("")) {
            return null;
        } else {
            return input;
        }
    }

    /**
     * Gets a yes or answer from the user
     * 
     * @return Boolean of user's answer. Yes: true, No: false
     */
    public static boolean promptYesNo() {
        return (promptOption("1: Yes.\n2: No.", 2) == 1);
    }
}
