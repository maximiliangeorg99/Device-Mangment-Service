package c24.thriftshop.Authentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    private String token;

    public LoginResponse(@JsonProperty("token") final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
