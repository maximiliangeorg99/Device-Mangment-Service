package c24.thriftshop.stripe.demo.domain.user;

import c24.thriftshop.stripe.demo.domain.Email;
import c24.thriftshop.stripe.demo.persistence.user.JsonUser;

import java.util.UUID;

public class User {
    private final Email email;
    private final Password password;
    private final UUID id;

    public User(final JsonUser jsonUser) {
        email = new Email(jsonUser.getEmail());
        //TODO Crappy implementation
        password = new Password(jsonUser.getPassword(), jsonUser.getSalt());
        id = UUID.randomUUID();
    }

    public User(final String email, final String password) {
        this.email = new Email(email);
        this.password = new Password(password);
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
