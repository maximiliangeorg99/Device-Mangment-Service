package c24.thriftshop.stripe.demo.domain.user;

import c24.thriftshop.stripe.demo.domain.Email;
import c24.thriftshop.stripe.demo.persistence.user.JsonUser;
import c24.thriftshop.stripe.demo.persistence.user.JsonUserRepository;

public class UserService {
    private final JsonUserRepository jsonUserRepository;

    public UserService(final JsonUserRepository jsonUserRepository) {
        this.jsonUserRepository = jsonUserRepository;
    }

    public boolean registerUser(final String email, final String password) {
        if (emailExists(new Email(email))) {
            //Error
            return false;
        } else {
            final User user = new User(email, password);
            jsonUserRepository.save(new JsonUser(user));
            return true;
        }
    }

    public boolean loginUser(final String email, final String password) {
        return emailExists(new Email(email));
    }

    private boolean emailExists(final Email email) {
        return jsonUserRepository.findByEmail(email.getEmailAsString()).isPresent();
    }

    private boolean isValidPassword(final Password password) {
        return false;
    }
}
