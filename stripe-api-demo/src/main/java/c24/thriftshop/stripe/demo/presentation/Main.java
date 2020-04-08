package c24.thriftshop.stripe.demo.presentation;

import c24.thriftshop.stripe.demo.domain.user.LoginState;
import c24.thriftshop.stripe.demo.domain.user.RegistrationState;
import c24.thriftshop.stripe.demo.domain.user.UserService;
import c24.thriftshop.stripe.demo.persistence.user.JsonUserRepository;

import java.util.Scanner;

public class Main {
    static final JsonUserRepository jsonUserRepository = new JsonUserRepository();
    static final UserService userService = new UserService(jsonUserRepository);

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Email: ");
            final String email = scanner.next();
            System.out.print("Password: ");
            final String password = scanner.next();
            System.out.println("Enter login or register:");
            final String input = scanner.next();
            if (input.equals("register")) registration(email, password);
            else if (input.equals("login")) login(email, password);
        }
    }

    public static void registration(final String email, final String password) {
        final RegistrationState state = userService.registerUser(email, password);
        System.out.println("Registration " + state.message());
    }

    public static void login(final String email, final String password) {
        final LoginState state = userService.loginUser(email, password);
        System.out.println("Login " + state.message());
    }
}