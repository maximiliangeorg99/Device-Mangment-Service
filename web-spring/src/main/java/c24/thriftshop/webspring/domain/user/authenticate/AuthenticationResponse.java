package c24.thriftshop.webspring.domain.user.authenticate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    String id;
    boolean successful;

    public AuthenticationResponse(@JsonProperty("successful") final boolean successful, @JsonProperty("id") final String id) {
        this.successful = successful;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
