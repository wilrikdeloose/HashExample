package BLL;

import Models.PBKDF2Encryptor;
import Models.PasswordEncryptor;

public class EncryptorFactory {
    static public PasswordEncryptor getEncryptor(EncryptionAlgorithms alg) {
        switch (alg) {
            case PBKDF2:
                return new PBKDF2Encryptor();
            default:
                return new PBKDF2Encryptor();
        }
    }
}
