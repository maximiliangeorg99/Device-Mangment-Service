package c24.thriftshop.console.command;

import c24.thriftshop.console.model.Device;
import c24.thriftshop.console.model.RentResponse;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

@CommandLine.Command(name = "rent", description = "Try to rent a device")
public class RentCommand implements Runnable {
    @CommandLine.Option(names = {"-d", "--device"}, required = true)
    private String device;

    @CommandLine.Option(names = {"-t", "--time"}, required = true)
    private String time;

    @CommandLine.Option(names = {"-id", "--deviceId"})
    private int deviceId;

    @Override
    public void run() {
        String token = "";
        try {
            final File tokens = new File("C:\\dev\\training\\Device-Managment-Service\\console\\src\\main\\resources", "tokens");
            token = Files.readString(tokens.toPath());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final String bodyString = (deviceId == 0) ? "{\n\t\"deviceName\" : \"" + device + "\",\n\t\"duration\": " + time + "\n}" : "{\n\t\"deviceName\" : \"" + device + "\",\n\t\"duration\": " + time + ",\n\t\"deviceId\": " + deviceId + "\n}";
        final HttpResponse<String> response = Unirest.post("http://localhost:8090/devices/rent")
                .header("cookie", "JSESSIONID=E5C032F83A34491461637C150F3B223C")
                .header("content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyString)
                .asString();
        if (response.isSuccess()) {
            final JSONObject jsonObject = new JSONObject(response.getBody());
            System.out.println(jsonObject.getString("message"));
            if (jsonObject.get("devices") != null) {
                final Gson gson = new Gson();
                final RentResponse rentResponse = gson.fromJson(response.getBody(), RentResponse.class);
                final ArrayList<Device> deviceList = rentResponse.getDevices().getList();
                deviceList.sort((o1, o2) -> {
                    if (o1.getDeviceId() == o2.getDeviceId()) {
                        return 0;
                    } else if (o1.getDeviceId() > o2.getDeviceId()) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                for (final Device d : deviceList) {
                    System.out.println("Id: " + d.getDeviceId() +
                            " Description: " + d.getDescription() +
                            " Available: " + d.isAvailable());
                }
            }
        } else {
            System.out.println("Rent failed");
        }
    }
}
