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
                    if ((pendingValue >= 0 && pendingValue <= optionCap)) {
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
}
