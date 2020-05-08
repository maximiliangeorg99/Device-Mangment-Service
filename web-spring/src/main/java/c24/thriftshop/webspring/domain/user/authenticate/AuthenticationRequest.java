package c24.thriftshop.webspring.domain.user.authenticate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationRequest {
    String token;

    public AuthenticationRequest(@JsonProperty(value = "token", required = true) final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
