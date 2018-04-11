package DAL;

import Models.Account;
import Models.PBKDF2Password;

public interface AccountContext {
    boolean login(String username, PBKDF2Password password);
    boolean register(String username, PBKDF2Password password);
    Account getAccountByUsername(String username);
    byte[] getSaltByUsername(String username);
}
