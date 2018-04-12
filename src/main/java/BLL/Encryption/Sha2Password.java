package BLL.Encryption;

import java.util.Arrays;

public class Sha2Password implements Password {
    private byte[] hash;
    private byte[] salt;
    private int iterations;
    private int keyLength;

    public Sha2Password(byte[] hash, byte[] salt, int iterations, int keyLength) {
        this.hash = hash;
        this.salt = salt;
        this.iterations = iterations;
        this.keyLength = keyLength;
    }

    public byte[] getSalt() {
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Sha2Password)) {
            return false;
        }

        Sha2Password p = (Sha2Password)o;
        boolean hashIsEqual = Arrays.equals(this.hash, p.hash);
        boolean saltIsEqual = Arrays.equals(this.salt, p.salt);
        boolean iterIsEqual = this.iterations == p.iterations;
        boolean klenIsEqual = this.keyLength == p.keyLength;

        return hashIsEqual && saltIsEqual && iterIsEqual && klenIsEqual;
    }
}
