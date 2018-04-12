package BLL.Encryption;

import java.util.Arrays;

public class PBKDF2Password implements Password {
    private byte[] hash;
    private byte[] salt;
    private int iterations;
    private int keyLength;

    public PBKDF2Password(byte[] hash, byte[] salt, int iterations, int keyLength) {
        this.hash = hash;
        this.salt = salt;
        this.iterations = iterations;
        this.keyLength = keyLength;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof PBKDF2Password)) {
            return false;
        }

        PBKDF2Password p = (PBKDF2Password)o;
        boolean hashIsEqual = Arrays.equals(this.hash, p.hash);
        boolean saltIsEqual = Arrays.equals(this.salt, p.salt);
        boolean iterIsEqual = this.iterations == p.iterations;
        boolean klenIsEqual = this.keyLength == p.keyLength;

        return hashIsEqual && saltIsEqual && iterIsEqual && klenIsEqual;
    }

    public byte[] getSalt() {
        return salt;
    }
}
