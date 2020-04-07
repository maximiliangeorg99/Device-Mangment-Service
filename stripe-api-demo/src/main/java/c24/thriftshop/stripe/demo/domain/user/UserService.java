package c24.thriftshop.stripe.demo.domain.user;

import c24.thriftshop.stripe.demo.domain.Email;
import c24.thriftshop.stripe.demo.persistence.user.JsonUser;
import c24.thriftshop.stripe.demo.persistence.user.JsonUserRepository;

public class UserService {
    private final JsonUserRepository jsonUserRepository;

    public UserService(final JsonUserRepository jsonUserRepository) {
        this.jsonUserRepository = jsonUserRepository;
    }

    //return if Registration was successful
    public boolean registerUser(final User user) {
        if (emailExists(user.getEmail())) {
            //Error
            return false;
        } else {
            jsonUserRepository.save(new JsonUser(user));
            return true;
        }
    }

    //return if Login was successful
    public boolean loginUser(final User user) {
        return emailExists(user.getEmail());
    }

    private boolean emailExists(final Email email) {
        return false;
    }
}
