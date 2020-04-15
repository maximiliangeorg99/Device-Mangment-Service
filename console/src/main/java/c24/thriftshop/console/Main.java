package c24.thriftshop.console;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(final String[] args) {
        home();
    }

    private static void home() {
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

    private static void rent(final String email) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Device name: ");
        final String device = scanner.next();
        System.out.println("Enter rent or return:");
        final String input = scanner.next();
        if (input.equals("rent")) {
            System.out.println("Enter return Date:");
            //TODO fix Dates
            final String returnDate = scanner.next(Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}"));
            rentDevice(email, device, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), returnDate);
        } else if (input.equals("return")) returnDevice(email, device);
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
        if (response.getBody().equals("was successful."))
            rent(email);
    }

    private static void rentDevice(final String email, final String deviceName, final String from, final String to) {
        //generate Post Request
        System.out.println("Device in Rent from " + from + " to " + to);
    }

    private static void returnDevice(final String email, final String deviceName) {
        //generate Post Request
    }
}
