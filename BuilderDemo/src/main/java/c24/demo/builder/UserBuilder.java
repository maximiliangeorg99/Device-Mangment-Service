package c24.demo.builder;

import c24.demo.domain.entity.User;
import org.jusecase.builders.Builder;

public class UserBuilder implements Builder<User> {

    private final User user = new User();
    private String name;
    private String password;
    private String rolle;

    @Override
    public User build() {
        return new User(name, password, rolle);
    }

    public UserBuilder mapped(final User entity) {
        withPassword(entity.getPassword());
        withName(entity.getName());
        withRolle(entity.getRolle());
        return this;
    }

    public UserBuilder withPassword(final String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withRolle(final String rolle) {
        this.rolle = rolle;
        return this;
    }
}
