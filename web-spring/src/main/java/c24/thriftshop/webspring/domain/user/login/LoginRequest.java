package c24.thriftshop.webspring.domain.user.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private final String username;
    private final String password;

    public LoginRequest(@JsonProperty(value = "username", required = true) final String username,
                        @JsonProperty(value = "password", required = true) final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
