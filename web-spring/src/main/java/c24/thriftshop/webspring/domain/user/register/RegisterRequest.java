package c24.thriftshop.webspring.domain.user.register;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    private final String email;
    private final String password;

    public RegisterRequest(@JsonProperty("email") final String email,
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
