package c24.thriftshop.Authentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    private final String id;
    private final boolean successful;

    public AuthResponse(@JsonProperty("id") final String id, @JsonProperty("successful") final boolean successful) {
        this.id = id;
        this.successful = successful;
    }

    public String getId() {
        return id;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
