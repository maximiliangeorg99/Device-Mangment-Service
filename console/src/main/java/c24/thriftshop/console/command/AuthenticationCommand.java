package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import picocli.CommandLine;

@CommandLine.Command(name = "authenticate", aliases = {"a"}, description = "Try to authenticate with token")
public class AuthenticationCommand implements Runnable {
    @CommandLine.Option(names = {"-t", "--token"}, required = true)
    private String token;

    @Override
    public void run() {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/authenticate")
                .header("Authorization", "Bearer " + token)
                .asString();
        System.out.println(response.getBody().equals("true") ? "authentication successful" : "authentication failed");
    }
}
