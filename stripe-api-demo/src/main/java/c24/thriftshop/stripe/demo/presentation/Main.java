package c24.thriftshop.stripe.demo.presentation;

import c24.thriftshop.stripe.demo.domain.user.User;
import c24.thriftshop.stripe.demo.persistence.user.JsonUser;
import c24.thriftshop.stripe.demo.persistence.user.JsonUserRepository;

public class Main {
    public static void main(final String[] args) {
        final JsonUserRepository jsonUserRepository = new JsonUserRepository();
        final User user = new User("Max@web.de", "12345678");
        final User user2 = new User("Maximilian@web.de", "12345678");
        jsonUserRepository.save(new JsonUser(user));
        jsonUserRepository.save(new JsonUser(user2));
        jsonUserRepository.delete(new JsonUser(user));
    }
}
