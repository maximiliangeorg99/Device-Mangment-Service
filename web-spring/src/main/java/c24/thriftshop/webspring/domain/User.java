package c24.thriftshop.webspring.domain;

import c24.thriftshop.webspring.persistance.UserEntity;

import java.util.UUID;

public class User {
    private final Email email;
    private final Password password;
    private final UUID id;

    public User(final String email, final String password) {
        this.email = new Email(email);
        this.password = new Password(password);
        id = UUID.randomUUID();
    }

    public User(final UserEntity userEntity) {
        email = new Email(userEntity.getEmail());
        //TODO Crappy implementation
        password = new Password(userEntity.getPassword(), userEntity.getSalt());
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
