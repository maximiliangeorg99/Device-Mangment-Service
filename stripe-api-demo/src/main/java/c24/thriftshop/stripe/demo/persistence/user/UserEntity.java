package c24.thriftshop.stripe.demo.persistence.user;

import c24.thriftshop.stripe.demo.domain.user.User;

import java.util.UUID;

public class UserEntity {
    private final String email;
    private final String password;
    private final String salt;
    private final UUID id;
    boolean isActive;

    public UserEntity(final String email, final String password) {
        this.id = UUID.randomUUID();
        this.email = email.toLowerCase();
        this.password = password;
        this.isActive = true;
        this.salt = "";
    }

    public UserEntity(final User user) {
        this.id = UUID.randomUUID();
        this.email = user.getEmail().getEmailAsString();
        this.password = user.getPassword().getHash();
        this.isActive = true;
        this.salt = user.getPassword().getSalt();
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserEntity userEntity = (UserEntity) o;

        return id.equals(userEntity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(final boolean active) {
        isActive = active;
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
