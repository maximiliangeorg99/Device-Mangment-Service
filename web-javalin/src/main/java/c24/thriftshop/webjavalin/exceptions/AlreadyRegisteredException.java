package c24.thriftshop.webjavalin.exceptions;

public class AlreadyRegisteredException extends Exception {
    public AlreadyRegisteredException(final String message) {
        super(message);
    }
}
