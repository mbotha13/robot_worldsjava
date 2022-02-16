package za.co.wethinkcode.toyrobot;

import java.util.Locale;

public abstract class Command {
    private final String name;
    private final String argument;

    public abstract boolean execute(Robot target);

    public  Command( String name ){
        this.name=name.trim().toLowerCase(Locale.ROOT);
        this.argument ="";

    }
    public Command(String name, String argument){
        this.name = name;
        this.argument = argument.trim();
    }

    public String getName() {
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");

        switch (args[0]) {
            case "shutdown":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

}
