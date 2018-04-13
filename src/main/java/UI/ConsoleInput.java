package UI;

import UI.Exceptions.InvalidInputException;
import java.util.Arrays;

public class ConsoleInput {
    private String command;
    private String[] args;

    public ConsoleInput(String input) throws InvalidInputException {
        String trimmedInput = input.trim();

        if (trimmedInput == null || trimmedInput.equals("")) {
            throw new InvalidInputException("ERROR: Invalid input!");
        }

        String[] array = trimmedInput.split("\\s+");
        if (array.length > 0) {
            command = array[0];
            args = Arrays.copyOfRange(array, 1, array.length);
        } else {
            throw new InvalidInputException("ERROR: Invalid input!");
        }
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}
