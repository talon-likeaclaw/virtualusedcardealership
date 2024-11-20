package usedcardealership.interaction;

import java.util.*;

public class Prompter {
    private Scanner reader;

    public Prompter() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Closes the reader
     * To be used at end of program.
     */
    public void close() {
        this.reader.close();
    }

    /**
     * General prompt to get user input
     * 
     * @return String - user input
     */
    private String prompt() {
        System.out.print("> ");
        return reader.nextLine();
    }

    public int promptOption(String question, int optionCap) {
        int value = -1;
        boolean invalidValue = true;

        if (!question.equals("")) {
            System.out.println(question);
        }

        while(invalidValue) {
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
    public int promptInt() {
        return promptOption("", 0);
    }

    /**
     * Prompts user to input vehicle ID
     * 
     * @return the vehicle ID the user chose
     */
    public int promptVehicleId() {
        System.out.println("\nSelect a Vehicle ID:");
        return promptInt();
    }
}
