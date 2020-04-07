package c24.thriftshop.stripe.demo.domain.User;

import c24.thriftshop.stripe.demo.persistence.User.JsonUser;

public class User {
    Email email;
    Password password;

    public User(final JsonUser jsonUser) {
        email = new Email(jsonUser.getEmail());
        password = new Password(jsonUser.getPassword());
    }

    public User(final Email email, final Password password) {
        this.email = email;
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
