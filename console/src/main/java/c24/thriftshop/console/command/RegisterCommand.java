package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import picocli.CommandLine;
import picocli.CommandLine.Option;


@CommandLine.Command(
        name = "register"
)
public class RegisterCommand implements Runnable {
    @Option(names = {"-e", "--email"}, required = true)
    private String email;

    @Option(names = {"-p", "--password"}, required = true)
    private String password;

    @Override
    public void run() {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/user/register")
                .header("content-type", "application/json")
                .body("{\n\t\"email\": \"" + email + "\",\n\t\"password\": \"" + password + "\"\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
