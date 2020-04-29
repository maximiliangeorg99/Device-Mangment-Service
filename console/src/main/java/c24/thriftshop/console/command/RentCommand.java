package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import picocli.CommandLine;

@CommandLine.Command(name = "rent", description = "Try to rent a device")
public class RentCommand implements Runnable {
    @CommandLine.Option(names = {"-e", "--email"}, required = true)
    private String email;

    @CommandLine.Option(names = {"-d", "--device"}, required = true)
    private String device;

    @CommandLine.Option(names = {"-t", "--time"}, required = true)
    private String time;

    @Override
    public void run() {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/device/rent")
                .header("cookie", "JSESSIONID=E5C032F83A34491461637C150F3B223C")
                .header("content-type", "application/json")
                .body("{\n\t\"name\" : \"" + device + "\",\n\t\"email\": \"" + email + ",\n\t\"duration\": " + time + "\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
