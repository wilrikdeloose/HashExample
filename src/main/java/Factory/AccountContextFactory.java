package Factory;

import DAL.AccountContext;
import DAL.AccountMemoryContext;
import DAL.ContextType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AccountContextFactory {
    static public AccountContext get(ContextType contextType) {
        switch (contextType) {
            case Memory:
                return new AccountMemoryContext();
            default:
                throw new NotImplementedException();
        }
    }
}
