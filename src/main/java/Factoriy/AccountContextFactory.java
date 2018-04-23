package Factoriy;

import DAL.AccountContext;
import DAL.AccountMemoryContext;
import DAL.ContextType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AccountContextFactory {
    public AccountContext get(ContextType contextType) {
        switch (contextType) {
            case Memory:
                return new AccountMemoryContext();
            default:
                throw new NotImplementedException();
        }
    }
}