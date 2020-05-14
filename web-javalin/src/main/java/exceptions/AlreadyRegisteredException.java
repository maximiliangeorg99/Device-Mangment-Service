package exceptions;

public class AlreadyRegisteredException extends Exception {
    public AlreadyRegisteredException(final String message) {
        super(message);
    }
}
