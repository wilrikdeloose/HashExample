package BLL.Encryption;

public class EncrypterFactory {
    static public PasswordEncrypter getEncrypter(EncryptionAlgorithm algorithm) {
        switch (algorithm) {
            default:
            case Simple:
                return new SimpleEncrypter();

            case SHA2:
                return new Sha2Encrypter();
        }
    }
}
