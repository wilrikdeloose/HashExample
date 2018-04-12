package BLL.Encryption;

public class EncryptorFactory {
    static public PasswordEncrypter getEncrypter(EncryptionAlgorithms alg) {
        switch (alg) {
            case PBKDF2:
                return new PBKDF2Encrypter();

            case PlainText:
            default:
                return new PlainTextEncrypter();
        }
    }
}
