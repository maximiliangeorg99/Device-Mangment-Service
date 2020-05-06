package c24.thriftshop.console.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import picocli.CommandLine;

@CommandLine.Command(name = "return", description = "Return device")
public class ReturnCommand implements Runnable {
    @CommandLine.Option(names = {"-u", "--username"}, required = true)
    private String username;

    @CommandLine.Option(names = {"-d", "--device"}, required = true)
    private String device;

    @Override
    public void run() {
        final HttpResponse<String> response = Unirest.post("http://localhost:8090/device/return")
                .header("cookie", "JSESSIONID=E5C032F83A34491461637C150F3B223C")
                .header("content-type", "application/json")
                .body("{\n\t\"deviceName\":\"" + device + "\",\n\t\"username\":\"" + username + "\"\n}")
                .asString();
        System.out.println((response.isSuccess()) ? "Return was successful" : "Return failed");
    }
}
