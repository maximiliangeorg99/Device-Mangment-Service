package exceptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException(final String message) {
        super(message);
    }
}
