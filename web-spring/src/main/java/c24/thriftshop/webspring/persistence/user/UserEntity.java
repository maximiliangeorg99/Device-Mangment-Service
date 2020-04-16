package c24.thriftshop.webspring.persistence.user;

import c24.thriftshop.webspring.domain.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

//TODO custom Tabellenname definieren
//TODO custom Spaltennamen definieren
//hibernate annotations

@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @Column(name = "ID")
    private final UUID id;
    @Column(name = "EMAIL")
    private final String email;
    @Column(name = "PASSWORD")
    private final String password;
    @Column(name = "SALT")
    private final String salt;
    @Column(name = "ISACTIVE")
    boolean isActive;

    //flat copy
    public UserEntity(final String email, final String password, final String salt, final UUID id, final boolean isActive) {
        this.id = id;
        this.email = email.toLowerCase();
        this.password = password;
        this.isActive = isActive;
        this.salt = salt;
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
