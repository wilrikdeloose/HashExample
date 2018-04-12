package BLL.Encryption;

import BLL.Encryption.Password;

public interface PasswordEncryptor {
    Password encrypt(String password);
}
