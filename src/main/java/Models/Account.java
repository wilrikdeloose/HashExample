package Models;

public class Account {
    private String username;
    private PBKDF2Password password;

    public Account(String username, PBKDF2Password password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public PBKDF2Password getPassword() {
        return password;
    }
}
