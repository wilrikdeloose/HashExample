package Encryption;

import java.util.Arrays;

public class Sha2Password extends Password {
    private byte[] hash;
    private int iterations;
    private int keyLength;

    public Sha2Password(byte[] hash, Salt salt, int iterations, int keyLength) {
        super(salt);
        this.hash = hash;
        this.iterations = iterations;
        this.keyLength = keyLength;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Sha2Password)) {
            return false;
        }

        Sha2Password p = (Sha2Password)o;
        boolean hashIsEqual = Arrays.equals(this.hash, p.hash);
        boolean saltIsEqual = super.salt.equals(p.getSalt());
        boolean iterIsEqual = this.iterations == p.iterations;
        boolean klenIsEqual = this.keyLength == p.keyLength;

        return hashIsEqual && saltIsEqual && iterIsEqual && klenIsEqual;
    }
}
