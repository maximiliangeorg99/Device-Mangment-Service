package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@CommandLine.Command(name = "rent", description = "Try to rent a device")
public class RentCommand implements Runnable {
    @CommandLine.Option(names = {"-d", "--device"}, required = true)
    private String device;

    @CommandLine.Option(names = {"-t", "--time"}, required = true)
    private String time;

    @Override
    public void run() {
        String token = "";
        try {
            final File tokens = new File("C:\\dev\\training\\Device-Managment-Service\\console\\src\\main\\resources", "tokens");
            token = Files.readString(tokens.toPath());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final HttpResponse<String> response = Unirest.post("http://localhost:8090/device/rent")
                .header("cookie", "JSESSIONID=E5C032F83A34491461637C150F3B223C")
                .header("content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n\t\"deviceName\" : \"" + device + "\",\n\t\"duration\": " + time + "\n}")
                .asString();
        if (response.isSuccess()) {
            final JSONObject jsonObject = new JSONObject(response.getBody());
            System.out.println(jsonObject.getString("message"));
        } else {
            System.out.println("Rent failed");
        }
    }
}
