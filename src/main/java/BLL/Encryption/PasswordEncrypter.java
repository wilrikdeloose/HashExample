package BLL.Encryption;

public interface PasswordEncrypter {
    Password encrypt(String password);
    Password encrypt(String password, byte[] salt);
}
