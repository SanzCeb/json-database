package server.database.command;

public class CommandError implements Command{
    @Override
    public void execute() {
        System.out.println("Invalid Command");
    }
}
