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
        String input = prompt();
        try {
            if (!input.equals("")) {
                int pendingValue = Integer.parseInt(input);
                if ((pendingValue >= 0 && pendingValue <= optionCap) || optionCap == 0) {
                    return pendingValue;
                } else {
                    PrettyUtils.printRed("\nInvalid option! Please choose a number from the list.");
                    promptEnter();
                }
            } else {
                PrettyUtils.printRed("\nInput cannot be empty!");
                promptEnter();
            }
        } catch (NumberFormatException e) {
            PrettyUtils.printRed("\nIllegal input! Input must be a number from the list.");
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
            } catch (NumberFormatException e) {
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
        System.out.print("Press " + PrettyUtils.returnYellowEnter() + " to continue:");
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
                return "\nType " + PrettyUtils.returnYellow("filter type") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "type":
                return "\nType " + PrettyUtils.returnYellow("vehicle type") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "make":
                return "\nType " + PrettyUtils.returnYellow("vehicle make") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "color":
                return "\nType " + PrettyUtils.returnYellow("vehicle color") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "year":
                return "\nType " + PrettyUtils.returnYellow("vehicle year range") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "drive":
                return "\nType " + PrettyUtils.returnYellow("vehicle drive type") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "price":
                return "\nType " + PrettyUtils.returnYellow("vehicle price range") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "kilo":
                return "\nType " + PrettyUtils.returnYellow("vehicle kilometrage range") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "trans":
                return "\nType " + PrettyUtils.returnYellow("vehicle transmission type") + " or press " + PrettyUtils.returnYellowEnter() + " to go back:";
            case "id-sort":
                return PrettyUtils.returnYellow("Sort Options:\n") +
                        "- Type: " + PrettyUtils.returnYellow("id, price, year, kilometrage, damage\n") +
                        "- Add " + PrettyUtils.returnYellow("`desc`") + " for descending order (`id desc`)\n" +
                        "Select a vehicle with it's " + PrettyUtils.returnYellow("[ID]") + ", or press " + PrettyUtils.returnYellowEnter() + " to go back.";
            default:
                return "No prompt available";
        }
    }
}
