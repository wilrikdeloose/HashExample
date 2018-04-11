package Models;

import BLL.Password;

public interface PasswordEncryptor {
    Password encrypt(String password);
}
