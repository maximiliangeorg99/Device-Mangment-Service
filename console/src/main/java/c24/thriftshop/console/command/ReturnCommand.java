package c24.thriftshop.console.command;

import picocli.CommandLine;

@CommandLine.Command(
        name = "return"
)
public class ReturnCommand implements Runnable {
    @CommandLine.Option(names = {"-d", "--device"}, required = true)
    private String device;

    @Override
    public void run() {

    }
}
