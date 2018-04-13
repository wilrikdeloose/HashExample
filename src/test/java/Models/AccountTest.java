package Models;

import BLL.Encryption.Password;
import BLL.Encryption.Salt;
import BLL.Encryption.Sha2Password;
import BLL.Encryption.SimplePassword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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