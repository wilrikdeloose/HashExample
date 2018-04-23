package BLL.Encryption;

import Factory.EncrypterFactory;
import Encryption.EncryptionAlgorithm;
import Encryption.Password;
import Encryption.PasswordEncrypter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncrypterTest {

    PasswordEncrypter[] encrypters;

    @BeforeEach
    public void initialize() {
        encrypters = new PasswordEncrypter[2];
        encrypters[0] = EncrypterFactory.get(EncryptionAlgorithm.SHA2);
        encrypters[1] = EncrypterFactory.get(EncryptionAlgorithm.Simple);
    }

    @Test
    void encryptTwoTimeSamePasswordGivesDifferentHash() {
        for (PasswordEncrypter enc: encrypters) {
            Password pwd1 = enc.encrypt("password");
            Password pwd2 = enc.encrypt("password");
            assertFalse(pwd1.equals(pwd2));
        }
    }

    @Test
    void encryptTwoTimeSameSaltGivesSameHash() {
        for (PasswordEncrypter enc: encrypters) {
            Password pwd1 = enc.encrypt("password");
            Password pwd2 = enc.encrypt("password", pwd1.getSalt());
            assertTrue(pwd1.equals(pwd2));
        }
    }

    /*
    TODO: This test fails about 50% of the time. The question to you is why?
     */
    @Test
    void encryptTwoTimeSameSaltDifferentPasswordGivesDifferentHash() {
        for (PasswordEncrypter enc: encrypters) {
            Password pwd1 = enc.encrypt("password1");
            Password pwd2 = enc.encrypt("password2", pwd1.getSalt());
            assertFalse(pwd1.equals(pwd2), "Failed for " + enc.getClass());
        }
    }
}