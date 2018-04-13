package BLL.Encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Salt {
    private final int SALT_SIZE = 32;
    private byte[] salt;

    public Salt() {
        salt = new byte[SALT_SIZE];
        try {
            SecureRandom.getInstanceStrong().nextBytes(salt);
        }  catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] get() {
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Salt)) {
            return false;
        }

        Salt s = (Salt)o;
        return Arrays.equals(this.salt, s.get());
    }
}
