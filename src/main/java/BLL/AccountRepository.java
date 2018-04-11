package BLL;

import BLL.Exceptions.*;
import DAL.AccountContext;
import DAL.AccountMemoryContext;
import Models.PBKDF2Encryptor;
import Models.PBKDF2Password;

public class AccountRepository {
    private final int USERNAME_MINIMAL_LENGTH = 6;
    private final int PASSWORD_MINIMAL_LENGTH = 10;

    // local memory context to work with
    AccountContext context = new AccountMemoryContext();

    // local encryptor object
    PBKDF2Encryptor encryptor = (PBKDF2Encryptor)EncryptorFactory.getEncryptor(EncryptionAlgorithms.PBKDF2);

    public void login(String username, String password) {

        byte[] salt = context.getSaltByUsername(username);
        PBKDF2Password safePassword = encryptor.encrypt(password, salt);

        if (context.login(username, safePassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login unsuccessful!");
        }
    }

    public void register(String username, String password) throws UsernameAlreadyExistsException, UsernameTooShortException, PasswordTooWeakException {
        if (usernameAlreadyExists(username)) {
            throw new UsernameAlreadyExistsException("ERROR: This username is already taken!");
        }
        if (usernameTooShort(username)) {
            throw new UsernameTooShortException("ERROR: The chosen username is too short!");
        }
        if (passwordToWeak(password)) {
            throw new PasswordTooWeakException("ERROR: The chosen password is too weak!");
        }

        // generate salt and hashed password
        PBKDF2Password safePassword = encryptor.encrypt(password);

        if (context.register(username, safePassword)) {
            System.out.println("Account successfully registered!");
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
