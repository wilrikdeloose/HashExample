package BLL.Encryption;

public interface Password {
    @Override
    boolean equals(Object o);
    byte[] getSalt();
}