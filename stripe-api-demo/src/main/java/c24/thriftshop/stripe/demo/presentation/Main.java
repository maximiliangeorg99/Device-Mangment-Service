package c24.thriftshop.stripe.demo.presentation;

import c24.thriftshop.stripe.demo.domain.user.UserService;
import c24.thriftshop.stripe.demo.persistence.user.JsonUserRepository;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final JsonUserRepository jsonUserRepository = new JsonUserRepository();
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Registration:");
        System.out.println("Please enter Email and Password");
        System.out.print("Email: ");
        final String email = scanner.next();
        System.out.print("Password: ");
        final String password = scanner.next();
        final UserService userService = new UserService(jsonUserRepository);
        userService.registerUser(email, password);
    }
}
