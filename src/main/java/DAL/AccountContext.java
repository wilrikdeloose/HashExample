package DAL;

import BLL.Encryption.Password;
import Models.Account;

public interface AccountContext {
    boolean login(String username, Password password);
    boolean register(String username, Password password);
    Account getAccountByUsername(String username);
    byte[] getSaltByUsername(String username);
}
