package c24.thriftshop.webspring.domain.user.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    private final LoginMessage message;
    private final String token;

    public LoginResponse(@JsonProperty("message") final LoginMessage message, @JsonProperty("token") final String token) {
        this.message = message;
        this.token = token;
    }

    public LoginMessage getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
