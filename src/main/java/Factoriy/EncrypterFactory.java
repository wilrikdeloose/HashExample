package Factoriy;

import Encryption.EncryptionAlgorithm;
import Encryption.PasswordEncrypter;
import Encryption.Sha2Encrypter;
import Encryption.SimpleEncrypter;

public class EncrypterFactory {
    static public PasswordEncrypter get(EncryptionAlgorithm algorithm) {
        switch (algorithm) {
            default:
            case Simple:
                return new SimpleEncrypter();

            case SHA2:
                return new Sha2Encrypter();
        }
    }
}
