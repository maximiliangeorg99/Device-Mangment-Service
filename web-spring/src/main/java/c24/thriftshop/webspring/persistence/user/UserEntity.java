package c24.thriftshop.webspring.persistence.user;

import c24.thriftshop.webspring.domain.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @Column(name = "ID")
    private UUID id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "SALT")
    private String salt;
    @Column(name = "TOKEN")
    private UUID token;
    @Column(name = "TOKEN_EXPERATION_DATE")
    private Date expirationDate;

    public UserEntity() {
    }

    public UserEntity(final User user) {
        this.id = UUID.randomUUID();
        this.email = user.getEmail().getEmailAsString();
        this.password = user.getPassword().getHash();
        this.token = null;
        this.salt = user.getPassword().getSalt();
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(final UUID token) {
        this.token = token;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(final String salt) {
        this.salt = salt;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }
}
