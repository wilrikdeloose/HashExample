package Domain;

import Encryption.Password;
import Encryption.Salt;
import Encryption.SimplePassword;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void getUsername() {
        Account a = new Account("username", null);
        boolean result = a.getUsername().equals("username");
    }

    @Test
    void getPassword() {
        Password p = new SimplePassword("password", new Salt());
        Account a = new Account("username", p);
        boolean result = a.getPassword().equals(p);
    }
}