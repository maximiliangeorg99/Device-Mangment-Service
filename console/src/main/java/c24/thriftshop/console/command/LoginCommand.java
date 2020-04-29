package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name = "login", aliases = {"l"}, description = "Try to login as a User with email and password")
public class LoginCommand implements Runnable {
    @Option(names = {"-e", "--email"}, required = true)
    private String email;

    @Option(names = {"-p", "--password"}, required = true)
    private String password;

    @Override
    public void run() {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/login")
                .header("content-type", "application/json")
                .body("{\n\t\"username\": \"" + email + "\",\n\t\"password\": \"" + password + "\"\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
