package c24.thriftshop.Authentication.model;

public class LoginResponse {
    private String token;

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
