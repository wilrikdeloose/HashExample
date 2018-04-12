package UI;

import BLL.AccountRepository;
import BLL.Exceptions.PasswordTooWeakException;
import BLL.Exceptions.UsernameAlreadyExistsException;
import BLL.Exceptions.UsernameTooShortException;
import UI.Exceptions.InvalidInputException;
import UI.Exceptions.UnknownCommandException;
import UI.Exceptions.WrongArgumentsException;

import java.util.Scanner;

public class ConsoleApp {
    public void run() {

        menu();
        System.out.print("> ");

        while (true) {
            try {
                ConsoleInput input = getInput();
                handleInput(input);
            } catch (InvalidInputException | UnknownCommandException | WrongArgumentsException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("> ");
        }
    }

    private ConsoleInput getInput() throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return new ConsoleInput(input);
    }

    private void handleInput(ConsoleInput input) throws UnknownCommandException, WrongArgumentsException {
        switch (input.getCommand()) {
            case "menu":
                menu();
                break;

            case "register":
                register(input.getArgs());
                break;

            case "login":
                login(input.getArgs());
                break;

            case "exit":
                exit();
                break;

            default:
                throw new UnknownCommandException("ERROR: Unknown command!");
        }
    }

    private void menu() {
        System.out.println("Welcome to HashExample!");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Register a new account using:");
        System.out.println("  register <username> <password>");
        System.out.println("Login to an existing account using:");
        System.out.println("  login <username> <password>");
        System.out.println();
        System.out.println("Type exit to close the application or type menu to see this menu again.");
    }

    private void register(String args[]) {
        String username = args[0];
        String password = args[1];

        try {
            (new AccountRepository()).register(username, password);
        }
        catch (UsernameAlreadyExistsException | UsernameTooShortException | PasswordTooWeakException e) {
            System.out.println(e.getMessage());
        }
    }

    private void login(String args[]) throws WrongArgumentsException {
        if (args.length != 2) {
            throw new WrongArgumentsException("ERROR: Wrong arguments!");
        }

        String username = args[0];
        String password = args[1];

        (new AccountRepository()).login(username, password);
    }

    private void exit() {
        System.out.println("Bye, bye!");
        System.exit(0);
    }
}