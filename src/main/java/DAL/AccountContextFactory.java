package DAL;

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
