package BLL.Encryption;

public interface Password {
    boolean equals(Object o);
    byte[] getSalt();
}