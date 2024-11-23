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
     * @param question  the question/menu options
     * @param optionCap the total amount of options (that are not 0)
     * @return the number the user has chosen
     */
    public static int promptOption(String question, int optionCap) {
        System.out.println(question);
        System.out.println("\nPlease select a number:");
        String input = prompt();
        try {
            if (!input.equals("")) {
                int pendingValue = Integer.parseInt(input);
                if ((pendingValue >= 0 && pendingValue <= optionCap) || optionCap == 0) {
                    return pendingValue;
                } else {
                    PrettyUtils.printRed("\nInvalid option! Please choose a number from the list.\n");
                    promptEnter();
                }
            } else {
                PrettyUtils.printRed("\nInput cannot be empty!\n");
                promptEnter();
            }
        } catch (NumberFormatException e) {
            PrettyUtils.printRed("\nIllegal input! Input must be a number from the list.\n");
            promptEnter();
        }
        return -1; // Return -1 for invalid input
    }

    /**
     * Gets an int and only an int from the user
     * 
     * @return the int the user chose
     */
    public static int promptInt() {
        while (true) {
            String input = prompt();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                PrettyUtils.printRed("\nInvalid input! Please enter a valid integer.\n");
            }
        }
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
        System.out.print("Press ");
        PrettyUtils.printYellow("ENTER");
        System.out.print(" to continue:");
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
        return input.toLowerCase();
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

    /**
     * Returns a unique prompt depending on the filter type
     * 
     * @param promptType the method we are filtering by
     * @return the prompt from the particular type of filter
     */
    public static String getPrompt(String promptType) {

        switch (promptType) {
            case "filter":
                return "\nEnter filter type or press ENTER to go back:";
            case "type":
                return "\nEnter vehicle type or press ENTER to go back:";
            case "make":
                return "\nEnter vehicle make or press ENTER to go back:";
            case "color":
                return "\nEnter vehicle color or press ENTER to go back:";
            case "year":
                return "\nEnter vehicle year range or press ENTER to go back:";
            case "drive":
                return "\nEnter vehicle drive type or press ENTER to go back:";
            case "price":
                return "\nEnter vehicle price range or press ENTER to go back:";
            case "kilo":
                return "\nEnter vehicle kilometrage range or press ENTER to go back:";
            case "trans":
                return "\nEnter vehicle transmission type or press ENTER to go back:";
            case "id-sort":
                return "Sort Options:\n" +
                        "- Type: id, price, year, kilometrage, damage\n" +
                        "- Add 'desc' for descending order ('id desc')\n" +
                        "To select a vehicle, enter its [ID] or press Enter to go back.";
            default:
                return "No prompt available";
        }
    }
}
