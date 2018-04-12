package Models;

import BLL.Encryption.Password;

public class Account {
    private String username;
    private Password password;

    public Account(String username, Password password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
