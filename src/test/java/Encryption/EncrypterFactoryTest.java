package BLL.Encryption;

import Encryption.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncrypterFactoryTest {

    class Enc {
        EncryptionAlgorithm ea;
        Class type;
        public Enc(EncryptionAlgorithm ea, Class type) { this.ea = ea; this.type = type; }
        public EncryptionAlgorithm getEncryptionAlgoritm() { return ea; }
        public Class getType() { return type;}
    }

    EncrypterFactory encrypterFactory;
    Enc[] encs = {
            new Enc(EncryptionAlgorithm.SHA2, Sha2Encrypter.class),
            new Enc(EncryptionAlgorithm.Simple, SimpleEncrypter.class)
    };

    @Test
    void getEncrypters() {
        for (Enc e: encs) {
            PasswordEncrypter passwordEncrypter = encrypterFactory.getEncrypter(e.getEncryptionAlgoritm());
            assertTrue(e.getType().isInstance(passwordEncrypter));
        }
    }
}