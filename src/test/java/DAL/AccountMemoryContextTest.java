package DAL;

import BLL.Encryption.Salt;
import BLL.Encryption.SimplePassword;
import Models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AccountMemoryContextTest {
    Salt salt = new Salt();
    SimplePassword pwd = new SimplePassword("password", salt.get());
    AccountContext[] contexts;

    @BeforeEach
    public void initialize() {
        contexts = new AccountContext[1];

        // clear the memory context
        AccountMemoryContext memCtx = new AccountMemoryContext();
        memCtx.clear();

        // add all available contexts to the array
        contexts[0] = memCtx;

        // add one account per context
        for (AccountContext ctx: contexts) {
            ctx.register("abcdef", pwd);
        }
    }

    @Test
    void loginExistingAccount() {
        for (AccountContext ctx: contexts) {
            boolean result = ctx.login("abcdef", pwd);
            assertTrue(result);
        }
    }

    @Test
    void loginMultipleTimesExistingSameAccount() {
        for (AccountContext ctx: contexts) {
            boolean result = true;
            for (int i = 0; i < 10; i++) {
                result = result && ctx.login("abcdef", pwd);
            }
            assertTrue(result);
        }
    }

    @Test
    void loginWrongUsername() {
        for (AccountContext ctx: contexts) {
            boolean result = ctx.login("fedcba", pwd);
            assertFalse(result);
        }
    }

    @Test
    void loginWrongPassword() {
        SimplePassword wrongPwd = new SimplePassword("paaswoord", (new Salt()).get());
        for (AccountContext ctx: contexts) {
            boolean result = ctx.login("abcdef", wrongPwd);
            assertFalse(result);
        }
    }

    @Test
    void registerNewNonExsistingAccount() {
        for (AccountContext ctx: contexts) {
            boolean result = ctx.register("fedcba", pwd);
            assertTrue(result);
        }
    }

    @Test
    void registerAlreadyExsistingAccount() {
        for (AccountContext ctx: contexts) {
            boolean result = ctx.register("abcdef", pwd);
            assertFalse(result);
        }
    }

    @Test
    void getExistingAccountByUsername() {
        for (AccountContext ctx: contexts) {
            Account a = ctx.getAccountByUsername("abcdef");
            assertTrue(a != null);
            assertTrue(a.getUsername().equals("abcdef"));
        }
    }

    @Test
    void getNonExistantAccount() {
        for (AccountContext ctx: contexts) {
            Account a = ctx.getAccountByUsername("fail");
            assertTrue(a == null);
        }
    }

    @Test
    void getSaltByUsernameOfExistingAccount() {
        for (AccountContext ctx: contexts) {
            byte[] s = ctx.getSaltByUsername("abcdef");
            boolean result = Arrays.equals(s, salt.get());
            assertTrue(result);
        }
    }

    @Test
    void getSaltByUsernameOfNonExistingAccount() {
        for (AccountContext ctx: contexts) {
            byte[] s = ctx.getSaltByUsername("fail");
            assertTrue(s == null);
        }
    }
}