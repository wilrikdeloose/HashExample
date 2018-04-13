package BLL.Encryption;

import java.util.Arrays;

public class SimplePassword implements Password {
    private String encryptedPassword;
    private byte[] salt;

    public SimplePassword(String password, byte[] salt) {
        this.encryptedPassword = password;
        this.salt = salt;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof SimplePassword)) {
            return false;
        }

        SimplePassword p = (SimplePassword)o;
        boolean passIsEqual = p.getEncryptedPassword().equals(this.encryptedPassword);
        boolean saltIsEqual = Arrays.equals(this.salt, p.getSalt());

        return saltIsEqual && passIsEqual;
    }
}
