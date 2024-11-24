package usedcardealership.interaction;

public class PrettyUtils {
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String CYAN = "\033[36m";

    /**
     * Prints a message in the specified color
     * 
     * @param message   the message to print
     * @param colorCode the ANSI color code for the text
     */
    private static void printColor(String message, String colorCode) {
        System.out.print(colorCode + message + RESET + "\n");
    }

    /**
     * Prints a message in red
     * 
     * @param message the message to print
     */
    public static void printRed(String message) {
        printColor(message, RED);
    }

    /**
     * Returns a message in red
     * 
     * @param message the message to covert
     * @return String- the message in red
     */
    public static String returnRed(String message) {
        return RED + message + RESET;
    }

    /**
     * Prints a message in green
     * 
     * @param message the message to print
     */
    public static void printGreen(String message) {
        printColor(message, GREEN);
    }

    /**
     * Returns a message in green
     * 
     * @param message the message to covert
     * @return String- the message in green
     */
    public static String returnGreen(String message) {
        return GREEN + message + RESET;
    }

    /**
     * Prints a message in yellow
     * 
     * @param message the message to print in yellow
     */
    public static void printYellow(String message) {
        printColor(message, YELLOW);
    }

    /**
     * Returns a message in yellow
     * 
     * @param message the message to convert
     * @return String - the message in yellow
     */
    public static String returnYellow(String message) {
        return YELLOW + message + RESET;
    }

    /**
     * Returns ENTER in yellow
     * 
     * @return String - ENTER in yellow
     */
    public static String returnYellowEnter() {
        return YELLOW + "Enter" + RESET;
    }

    /**
     * Prints a message in cyan
     * @param message the message to print
     */
    public static void printCyan(String message) {
        printColor(message, CYAN);
    }

    /**
     * Returns a message in cyan
     * 
     * @param message the message to convert
     * @return String - the message in cyan
     */
    public static String returnCyan(String message) {
        return CYAN + message + RESET;
    }

    public static String returnCars() {
        return "      ______                   ______\n" +
                "   __//__|__\\___           ___/__|__\\\\___\n" +
                " _|  _     _    |         |    _     _  |_\n" +
                "|_|-(_)-------(_)---------(_)-------(_)-|_|\n";
    }

    /**
     * Wipes the console screen0
     */
    public static void wipe() {
        System.out.print("\033[H\033[2J");
    }
}
