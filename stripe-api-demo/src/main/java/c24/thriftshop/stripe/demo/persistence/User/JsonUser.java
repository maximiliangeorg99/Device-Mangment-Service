package c24.thriftshop.stripe.demo.persistence.User;

public class JsonUser {
    String email;
    String password;

    public JsonUser(final String email, final String password) {
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
