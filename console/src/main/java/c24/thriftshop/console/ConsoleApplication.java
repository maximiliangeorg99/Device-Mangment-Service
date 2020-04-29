package c24.thriftshop.console;

import c24.thriftshop.console.command.*;
import picocli.CommandLine;

public class ConsoleApplication {

    public static void main(final String[] args) {

        final CommandLine commandLine = new CommandLine(new DevicesCommand());
        commandLine.addSubcommand("login", new LoginCommand());
        commandLine.addSubcommand("rent", new RentCommand());
        commandLine.addSubcommand("return", new ReturnCommand());
        commandLine.addSubcommand("authenticate", new AuthenticationCommand());

        //final String[] inputs = {"l", "-e", "gauss", "-p", "password"};
        //final String[] inputs = {"a", "-t", "af21193b-abfc-44d2-aa45-d34223b6fae1"};

        commandLine.parseWithHandler(new CommandLine.RunLast(), args);
    }
}