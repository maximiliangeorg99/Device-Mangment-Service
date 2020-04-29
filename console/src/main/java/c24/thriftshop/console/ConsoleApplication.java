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

        final String[] inputs = {"l", "-e", "gauss", "-p", "password"};
        //final String[] inputs = {"a", "-t", "026d1786-a1c2-4508-a228-3cba295cefe9"};

        commandLine.parseWithHandler(new CommandLine.RunLast(), inputs);
    }
}