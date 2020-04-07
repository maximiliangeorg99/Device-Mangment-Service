package c24.thriftshop.stripe.demo.persistence.user;

import c24.thriftshop.stripe.demo.domain.user.User;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class JsonUser {
    private final String email;
    private final String password;
    private final UUID id;

    public JsonUser(final String email, final String password) {
        this.id = UUID.randomUUID();
        this.email = email.toLowerCase();
        this.password = password;
    }

    public JsonUser(final User user) {
        this.id = UUID.randomUUID();
        this.email = user.getEmail().getEmailAsString();
        this.password = new String(user.getPassword().getHash(), StandardCharsets.UTF_8);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }
}
