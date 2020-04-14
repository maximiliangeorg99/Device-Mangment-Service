package c24.thriftshop.console;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Scanner;

public class Main {
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

    private static void registration(final String email, final String password) {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/user/register")
                .header("content-type", "application/json")
                .body("{\n\t\"email\": \"" + email + "\",\n\t\"password\": \"" + password + "\"\n}")
                .asString();
        System.out.println(response.getBody());
    }

    private static void login(final String email, final String password) {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/user/login")
                .header("content-type", "application/json")
                .body("{\n\t\"email\": \"" + email + "\",\n\t\"password\": \"" + password + "\"\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
