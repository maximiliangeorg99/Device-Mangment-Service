package c24.thriftshop.webspring.domain.user.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private final String email;
    private final String password;

    public LoginRequest(@JsonProperty("email") final String email,
                        @JsonProperty("password") final String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
