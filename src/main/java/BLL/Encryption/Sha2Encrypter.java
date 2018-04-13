package BLL.Encryption;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Sha2Encrypter implements PasswordEncrypter {
    private final int ITERATIONS = 10000;
    private final int KEYLENGTH = 256;
    private final int SALT_SIZE = 32;

    @Override
    public Sha2Password encrypt(String password) {
        return this.encrypt(password, null);
    }

    public Sha2Password encrypt(String password, Salt inputSalt) {
        Salt salt = inputSalt == null ? new Salt() : inputSalt;

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.get(), ITERATIONS, KEYLENGTH);
            SecretKey key = skf.generateSecret(spec);
            byte[] hash = key.getEncoded();

            return new Sha2Password(hash, salt, ITERATIONS, KEYLENGTH);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
