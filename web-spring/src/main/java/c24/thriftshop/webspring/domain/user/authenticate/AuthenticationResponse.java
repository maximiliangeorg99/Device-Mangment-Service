package c24.thriftshop.webspring.domain.user.authenticate;

public class AuthenticationResponse {
    boolean successful;

    public AuthenticationResponse(final boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(final boolean successful) {
        this.successful = successful;
    }
}
