package Encryption;

public interface PasswordEncrypter {
    Password encrypt(String password);
    Password encrypt(String password, Salt salt);
}
