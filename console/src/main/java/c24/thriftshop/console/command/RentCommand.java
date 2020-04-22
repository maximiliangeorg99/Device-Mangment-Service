package c24.thriftshop.console.command;

import picocli.CommandLine;

@CommandLine.Command(
        name = "rent"
)
public class RentCommand implements Runnable {
    @CommandLine.Option(names = {"-d", "--device"}, required = true)
    private String device;

    @CommandLine.Option(names = {"-t", "--time"}, required = true)
    private String time;

    @Override
    public void run() {

    }
}
