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
     * Makes the user press Enter to continue
     */
    public static void promptEnter() {
        System.out.println("\nPress ENTER to continue:");
        reader.nextLine();
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
