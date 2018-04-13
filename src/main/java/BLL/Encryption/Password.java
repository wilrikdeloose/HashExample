package BLL.Encryption;

public abstract class Password {
    protected Salt salt;

    public Password() {
        // empty
    }

    public Password(Salt salt) {
        this.salt = salt;
    }

    public Salt getSalt() {
        return salt;
    }

    public abstract boolean equals(Object o);
}