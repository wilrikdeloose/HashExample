package BLL.Encryption;

public class EncrypterFactory {
    static public PasswordEncrypter getEncrypter(EncryptionAlgorithms algorithm) {
        switch (algorithm) {
            default:
            case SHA2:
                return new Sha2Encrypter();
        }
    }
}
