package BLL.Encryption;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PBKDF2Encrypter implements PasswordEncrypter {
    private final int ITERATIONS = 10000;
    private final int KEYLENGTH = 256;

    private byte[] salt = null;

    @Override
    public PBKDF2Password encrypt(String password) {
        if (salt == null) {
            salt = generateSalt();
        }

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEYLENGTH);
            SecretKey key = skf.generateSecret(spec);
            byte[] hash = key.getEncoded();

            return new PBKDF2Password(hash, salt, ITERATIONS, KEYLENGTH);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[32];
        (new SecureRandom()).nextBytes(salt);
        return salt;
    }
}