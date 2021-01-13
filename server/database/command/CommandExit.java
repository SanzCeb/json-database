package server.database.command;

public class CommandExit implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }
}
