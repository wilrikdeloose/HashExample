package UI;

import UI.Exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputTest {

    @Test
    void getCommand() throws InvalidInputException {
        ConsoleInput input = new ConsoleInput("command");

        boolean result = true;
        result = result && input.getCommand().equals("command");
        result = result && (Arrays.equals(input.getArgs(), new String[0]));

        assertTrue(result);
    }

    @Test
    void instantiateWithStrangeConsoleInput() {
        String[] input = { System.getProperty("line.separator"), "", "\t", "\0" };

        for (String i: input) {
            try {
                new ConsoleInput(i);
                fail("ConsoleInput should have thrown an InvalidInputException!");
            } catch (InvalidInputException e) {
                // pass
            }
        }
    }

    @Test
    void getOneArgument() throws InvalidInputException {
        ConsoleInput input = new ConsoleInput("command one");
        String cmd = input.getCommand();
        String argOne = input.getArgs()[0];

        assertTrue(cmd.equals("command"));
        assertTrue(argOne.equals("one"));
    }

    @Test
    void getLotsOfArguments() throws InvalidInputException {
        ConsoleInput input = new ConsoleInput("command 0 1 2 3 4 5 6 7 8 9");
        String cmd = input.getCommand();
        String[] args = input.getArgs();

        assertTrue(cmd.equals("command"));
        for (int i = 0; i < args.length; i++) {
            assertTrue(args[i].equals(Integer.toString(i)));
        }
    }
}