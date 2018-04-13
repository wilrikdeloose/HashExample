package BLL.Encryption;

import java.util.Arrays;

public class SimplePassword extends Password {
    private String encryptedPassword;

    public SimplePassword(String password, Salt salt) {
        super(salt);
        this.encryptedPassword = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof SimplePassword)) {
            return false;
        }

        SimplePassword p = (SimplePassword)o;
        boolean passIsEqual = p.getEncryptedPassword().equals(this.encryptedPassword);
        boolean saltIsEqual = this.salt.equals(p.getSalt());

        return saltIsEqual && passIsEqual;
    }
}
