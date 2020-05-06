package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Command(name = "login", aliases = {"l"}, description = "Try to login as a User with username and password")
public class LoginCommand implements Runnable {
    @Option(names = {"-u", "--username"}, required = true)
    private String username;

    @Option(names = {"-p", "--password"}, required = true)
    private String password;

    @Override
    public void run() {
        final HttpResponse<String> response = Unirest.post("http://localhost:8090/user/login")
                .header("content-type", "application/json")
                .body("{\n\t\"username\": \"" + username + "\",\n\t\"password\": \"" + password + "\"\n}")
                .asString();
        try {
            final File tokens = new File("C:\\dev\\training\\Device-Managment-Service\\console\\src\\main\\resources", "tokens");
            final JSONObject jsonObject = new JSONObject(response.getBody());
            Files.writeString(tokens.toPath(), jsonObject.getString("token"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        System.out.println((response.isSuccess()) ? "Login successful" : "Login failed");
    }
}
