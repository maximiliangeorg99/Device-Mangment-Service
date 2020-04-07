package c24.thriftshop.stripe.demo.domain.user;

import c24.thriftshop.stripe.demo.domain.Email;
import c24.thriftshop.stripe.demo.persistence.user.JsonUser;

public class User {
    private final Email email;
    private final Password password;

    public User(final JsonUser jsonUser) {
        email = new Email(jsonUser.getEmail());
        password = new Password(jsonUser.getPassword());
    }

    public User(final String email, final String password) {
        this.email = new Email(email);
        this.password = new Password(password);
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
