package BLL.Exceptions;

public class UsernameTooShortException extends Exception {
    public UsernameTooShortException(String message) {
        super(message);
    }
}
