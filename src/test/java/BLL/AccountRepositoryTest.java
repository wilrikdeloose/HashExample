package BLL;

import BLL.Exceptions.PasswordTooWeakException;
import BLL.Exceptions.UsernameAlreadyExistsException;
import BLL.Exceptions.UsernameTooShortException;
import Models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    private AccountRepository repo;

    @BeforeEach
    public void initialize() {
        repo = new AccountRepository();

        try {
            repo.register("abcdef", "1234567890");
        } catch (UsernameAlreadyExistsException | UsernameTooShortException | PasswordTooWeakException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginWithKnownUserAndCorrectPassword() {
        boolean result = repo.login("abcdef", "1234567890");
        assertTrue(result);
    }

    @Test
    void loginWithKnownUserAndWrongPassword() {
        boolean result = repo.login("abcdef", "9876543210");
        assertFalse(result);
    }

    @Test
    void loginWithUnknownUser() {
        boolean result = repo.login("username", "password");
        assertFalse(result);
    }

    @Test
    void registerNewUser() throws PasswordTooWeakException, UsernameTooShortException, UsernameAlreadyExistsException {
        boolean result = repo.register("fedcba", "9876543210");
        assertTrue(result);
    }

    @Test
    void registerNewUserThatAlreadyExists() throws PasswordTooWeakException, UsernameTooShortException {
        try {
            repo.register("abcdef", "password");
            fail("UsernameAlreadyExistsException expected but not thrown");
        } catch (UsernameAlreadyExistsException e) {
            // pass
        }
    }

    @Test
    void registerNewUserWithWeakPassword() throws UsernameAlreadyExistsException, UsernameTooShortException {
        try {
            repo.register("fedcba", "pwd");
            fail("PasswordTooWeakException expected but not thrown");
        } catch (PasswordTooWeakException e) {
            // pass
        }
    }

    @Test
    void registerNewUserWitShortUsername() throws PasswordTooWeakException, UsernameAlreadyExistsException {
        try {
            repo.register("fail", "9876543210");
            fail("UsernameTooShortException expected but not thrown");
        } catch (UsernameTooShortException e) {
            // pass
        }
    }

    @Test
    void registerTwoUsersAndLoginTwice() throws PasswordTooWeakException, UsernameAlreadyExistsException, UsernameTooShortException {
        repo.register("qwerty", "1234567890");
        repo.register("asdfgh", "1234567890");

        boolean result1 = repo.login("qwerty", "1234567890");
        boolean result2 = repo.login("asdfgh", "1234567890");

        assertTrue(result1);
        assertTrue(result2);
    }
}