package c24.thriftshop.stripe.demo.domain.User;

public class Password {
    private final String password;

    public Password(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
