package DAL;

import Encryption.Password;
import Encryption.Salt;
import Domain.Account;

public interface AccountContext {
    boolean login(String username, Password password);
    boolean register(String username, Password password);
    Account getAccountByUsername(String username);
    Salt getSaltByUsername(String username);
}
