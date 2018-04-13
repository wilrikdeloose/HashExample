package BLL.Encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SimpleEncrypter implements PasswordEncrypter {
    private final int SALT_SIZE = 32;

    @Override
    public SimplePassword encrypt(String password) {
        return encrypt(password, null);
    }

    @Override
    public SimplePassword encrypt(String password, byte[] inputSalt) {
        byte[] salt = inputSalt == null ? generateSalt() : inputSalt;
        byte[] encryptedPass = new byte[password.length()];

        byte s = 0;
        for (int i = 0; i < salt.length; i++) { s = (byte)(s + salt[i]); }

        // worst encryption ever...
        for (int i = 0; i < encryptedPass.length; i++) {
            byte b = (byte)(password.toCharArray()[i]);
            encryptedPass[i] = (byte)(b + s);
        }

        return new SimplePassword(new String(encryptedPass), salt);
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[SALT_SIZE];
        try {
            SecureRandom.getInstanceStrong().nextBytes(salt);
        }  catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return salt;
    }
}
