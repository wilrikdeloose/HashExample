package UI;

import UI.Exceptions.InvalidInputException;
import java.util.Arrays;

public class ConsoleInput {
    private String command;
    private String[] args;

    public ConsoleInput(String input) throws InvalidInputException {
        if (input == null) {
            throw new InvalidInputException("ERROR: Invalid input!");
        }

        String[] array = input.split("\\s+");
        command = array[0];
        args = Arrays.copyOfRange(array, 1, array.length);
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}
