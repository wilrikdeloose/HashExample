package BLL;

import BLL.Exceptions.PasswordTooWeakException;
import BLL.Exceptions.UsernameAlreadyExistsException;
import BLL.Exceptions.UsernameTooShortException;
import Factoriy.AccountContextFactory;
import DAL.ContextType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountLogicTest {

    private AccountLogic accountLogic;

    @BeforeEach
    public void initialize() {
        accountLogic = new AccountLogic((new AccountContextFactory()).get(ContextType.Memory));

        try {
            accountLogic.register("abcdef", "1234567890");
        } catch (UsernameAlreadyExistsException | UsernameTooShortException | PasswordTooWeakException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginWithKnownUserAndCorrectPassword() {
        boolean result = accountLogic.login("abcdef", "1234567890");
        assertTrue(result);
    }

    @Test
    void loginWithKnownUserAndWrongPassword() {
        boolean result = accountLogic.login("abcdef", "9876543210");
        assertFalse(result);
    }

    @Test
    void loginWithUnknownUser() {
        boolean result = accountLogic.login("username", "password");
        assertFalse(result);
    }

    @Test
    void registerNewUser() throws PasswordTooWeakException, UsernameTooShortException, UsernameAlreadyExistsException {
        boolean result = accountLogic.register("fedcba", "9876543210");
        assertTrue(result);
    }

    @Test
    void registerNewUserThatAlreadyExists() throws PasswordTooWeakException, UsernameTooShortException {
        try {
            accountLogic.register("abcdef", "password");
            fail("UsernameAlreadyExistsException expected but not thrown");
        } catch (UsernameAlreadyExistsException e) {
            // pass
        }
    }

    @Test
    void registerNewUserWithWeakPassword() throws UsernameAlreadyExistsException, UsernameTooShortException {
        try {
            accountLogic.register("fedcba", "pwd");
            fail("PasswordTooWeakException expected but not thrown");
        } catch (PasswordTooWeakException e) {
            // pass
        }
    }

    @Test
    void registerNewUserWitShortUsername() throws PasswordTooWeakException, UsernameAlreadyExistsException {
        try {
            accountLogic.register("fail", "9876543210");
            fail("UsernameTooShortException expected but not thrown");
        } catch (UsernameTooShortException e) {
            // pass
        }
    }

    @Test
    void registerTwoUsersAndLoginTwice() throws PasswordTooWeakException, UsernameAlreadyExistsException, UsernameTooShortException {
        accountLogic.register("qwerty", "1234567890");
        accountLogic.register("asdfgh", "1234567890");

        boolean result1 = accountLogic.login("qwerty", "1234567890");
        boolean result2 = accountLogic.login("asdfgh", "1234567890");

        assertTrue(result1);
        assertTrue(result2);
    }
}