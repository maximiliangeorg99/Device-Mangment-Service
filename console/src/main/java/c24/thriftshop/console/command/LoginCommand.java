package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@Command(
        name = "login"
)
public class LoginCommand implements Runnable {
    @Option(names = {"-e", "--email"}, required = true)
    private String email;

    @Option(names = {"-p", "--password"}, required = true)
    private String password;

    @Override
    public void run() {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/user/login")
                .header("content-type", "application/json")
                .body("{\n\t\"email\": \"" + email + "\",\n\t\"password\": \"" + password + "\"\n}")
                .asString();
        try {
            final FileWriter writer = new FileWriter(new File("C:\\dev\\training\\Device-Managment-Service\\console\\src\\main\\resources\\tokens ", "tokens.json"));
            writer.write(response.getBody());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.getBody());
    }
}
