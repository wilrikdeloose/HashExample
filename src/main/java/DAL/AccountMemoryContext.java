package DAL;

import BLL.Encryption.Password;
import Models.Account;
import java.util.ArrayList;

public class AccountMemoryContext implements AccountContext {
    static private ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public boolean login(String username, Password password) {
        for (Account a: accounts) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean register(String username, Password password) {
        if (getAccountByUsername(username) == null) {
            accounts.add(new Account(username, password));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Account getAccountByUsername(String username) {
        for (Account a: accounts) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public byte[] getSaltByUsername(String username) {
        Account a = getAccountByUsername(username);

        if (a != null) {
            return a.getPassword().getSalt();
        } else {
            return new byte[0];
        }
    }
}
