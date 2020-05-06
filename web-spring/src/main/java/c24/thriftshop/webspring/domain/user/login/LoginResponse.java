package c24.thriftshop.webspring.domain.user.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    private final boolean successful;
    private final String token;

    public LoginResponse(@JsonProperty("successful") final boolean successful, @JsonProperty("token") final String token) {
        this.successful = successful;
        this.token = token;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getToken() {
        return token;
    }
}
