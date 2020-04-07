package c24.thriftshop.stripe.demo.domain.User;

import c24.thriftshop.stripe.demo.persistence.User.JsonUserRepository;

public class UserService {
    private final JsonUserRepository jsonUserRepository;

    public UserService(final JsonUserRepository jsonUserRepository) {
        this.jsonUserRepository = jsonUserRepository;
    }

}
