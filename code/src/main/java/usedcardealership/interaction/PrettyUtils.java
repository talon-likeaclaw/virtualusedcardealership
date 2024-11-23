package usedcardealership.interaction;

public class PrettyUtils {
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";
    private static final String CYAN = "\033[36m";

    /**
     * Prints a message in the specified color
     * 
     * @param message   the message to print
     * @param colorCode the ANSI color code for the text
     */
    private static void printColor(String message, String colorCode) {
        System.out.print(colorCode + message + RESET);
    }

    /**
     * Prints a message in red
     */
    public static void printRed(String message) {
        printColor(message, RED);
    }

    /**
     * Prints a message in green
     */
    public static void printGreen(String message) {
        printColor(message, GREEN);
    }

    /**
     * Prints a message in yellow
     */
    public static void printYellow(String message) {
        printColor(message, YELLOW);
    }

    /**
     * Prints a message in blue
     */
    public static void printBlue(String message) {
        printColor(message, BLUE);
    }

    /**
     * Prints a message in cyan
     */
    public static void printCyan(String message) {
        printColor(message, CYAN);
    }

    /**
     * Wipes the console screen
     */
    public static void wipe() {
        System.out.print("\033[H\033[2J");
    }
}
