package c24.thriftshop.console;

import c24.thriftshop.console.command.*;
import picocli.CommandLine;

public class ConsoleApplication {

    public static void main(final String[] args) {
        final CommandLine commandLine = new CommandLine(new DeviceServiceCommand());
        commandLine.addSubcommand("register", new RegisterCommand());
        commandLine.addSubcommand("login", new LoginCommand());
        commandLine.addSubcommand("rent", new RentCommand());
        commandLine.addSubcommand("return", new ReturnCommand());

        commandLine.parseWithHandler(new CommandLine.RunLast(), args);
    }
}