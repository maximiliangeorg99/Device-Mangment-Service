package c24.demo;

import c24.demo.builder.UserBuilder;
import c24.demo.domain.entity.User;
import c24.demo.domain.service.FakeLoginService;
import c24.demo.persistance.InMemRepo;
import org.jusecase.inject.Injector;

import static org.jusecase.Builders.a;

public class Main {
    public static void main(final String[] args) {
        Injector.getInstance().add(new InMemRepo());
        final User user = a(user().withName("Max").withPassword("1234").withRolle("admin"));
        final FakeLoginService loginService = new FakeLoginService();
        loginService.loginUser(user);
    }

    public static UserBuilder user() {
        return new UserBuilder();
    }
}
