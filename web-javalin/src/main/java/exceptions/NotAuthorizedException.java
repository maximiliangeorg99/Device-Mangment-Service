package exceptions;

public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(final String message) {
        super(message);
    }
}
