package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@CommandLine.Command(name = "authenticate", aliases = {"a"}, description = "Try to authenticate with token")
public class AuthenticationCommand implements Runnable {
    private String token;

    @Override
    public void run() {
        try {
            final File tokens = new File("C:\\dev\\training\\Device-Managment-Service\\console\\src\\main\\resources", "tokens");
            token = Files.readString(tokens.toPath());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/authenticate")
                .header("Authorization", "Bearer " + token)
                .asString();
        System.out.println(response.getBody().equals("true") ? "authentication successful" : "authentication failed");
    }
}