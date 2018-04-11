package BLL.Exceptions;

public class PasswordTooWeakException extends Exception {
    public PasswordTooWeakException(String message) {
        super(message);
    }
}
