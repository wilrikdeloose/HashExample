package BLL;

import BLL.Exceptions.*;
import DAL.AccountContext;
import Encryption.*;
import Factoriy.EncrypterFactory;

public class AccountLogic {
    private final int USERNAME_MINIMAL_LENGTH = 6;
    private final int PASSWORD_MINIMAL_LENGTH = 10;

    // local memory context to work with
    AccountContext context;

    // local encryptor object
    PasswordEncrypter encrypter;

    public AccountLogic(AccountContext context) {
        this.context = context;
        encrypter = EncrypterFactory.get(EncryptionAlgorithm.SHA2);
    }

    public boolean login(String username, String passwordString) {
        Salt salt = context.getSaltByUsername(username);
        Password password = encrypter.encrypt(passwordString, salt);

        boolean result = context.login(username, password);
        if (result) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login unsuccessful :(");
        }
        return result;
    }

    public boolean register(String username, String passwordString) throws UsernameAlreadyExistsException, UsernameTooShortException, PasswordTooWeakException {
        if (usernameAlreadyExists(username)) {
            throw new UsernameAlreadyExistsException("ERROR: This username is already taken!");
        }
        if (usernameTooShort(username)) {
            throw new UsernameTooShortException("ERROR: The chosen username is too short!");
        }
        if (passwordToWeak(passwordString)) {
            throw new PasswordTooWeakException("ERROR: The chosen password is too weak!");
        }

        // generate salt and hashed password
        Password password = encrypter.encrypt(passwordString);

        if (context.register(username, password)) {
            System.out.println("Account successfully registered!");
            return true;
        } else {
            System.out.println("Account was not registered :(");
            return false;
        }
    }

    private boolean usernameAlreadyExists(String username) {
        return (context.getAccountByUsername(username) != null);
    }

    private boolean usernameTooShort(String username) {
        if (username.length() < USERNAME_MINIMAL_LENGTH) {
            return true;
        } else {
            return false;
        }
    }

    private boolean passwordToWeak(String password) {
        if (password.length() < PASSWORD_MINIMAL_LENGTH) {
            return true;
        } else {
            return false;
        }
    }
}
