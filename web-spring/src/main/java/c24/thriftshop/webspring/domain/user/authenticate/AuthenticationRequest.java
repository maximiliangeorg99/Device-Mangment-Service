package c24.thriftshop.webspring.domain.user.authenticate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationRequest {
    String username;

    public AuthenticationRequest(@JsonProperty("username") final String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
