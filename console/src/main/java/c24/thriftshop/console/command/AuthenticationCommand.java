package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@CommandLine.Command(name = "authenticate", aliases = {"a"}, description = "Try to authenticate with token")
public class AuthenticationCommand implements Runnable {

    @Override
    public void run() {
        String token = "";
        try {
            final File tokens = new File("C:\\dev\\training\\Device-Managment-Service\\console\\src\\main\\resources", "tokens");
            token = Files.readString(tokens.toPath());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final HttpResponse<String> response = Unirest.post("http://localhost:8090/user/authenticate")
                .header("Authorization", "Bearer " + token)
                .asString();
        final JSONObject jsonObject = new JSONObject(response.getBody());
        System.out.println(jsonObject.getBoolean("successful") ? "authentication successful" : "authentication failed");
    }
}