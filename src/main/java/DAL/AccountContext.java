package DAL;

import BLL.Encryption.Password;
import BLL.Encryption.Salt;
import Models.Account;

public interface AccountContext {
    boolean login(String username, Password password);
    boolean register(String username, Password password);
    Account getAccountByUsername(String username);
    Salt getSaltByUsername(String username);
}
