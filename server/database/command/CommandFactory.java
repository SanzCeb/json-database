package server.database.command;

import server.database.JSONDatabase;

import java.util.Scanner;

public class CommandFactory {
    private final JSONDatabase jsonDatabase;
    public CommandFactory(JSONDatabase jsonDatabase) {
        this.jsonDatabase = jsonDatabase;
    }

    public Command parseCommand(String command) {
        var scanner = new Scanner(command);
        Command result;
        try {
            var commandName = scanner.next("\\w+");
            int index;
            switch (commandName.toLowerCase()) {
                case "get":
                    index = Integer.parseInt(scanner.nextLine().trim());
                    result = new CommandGet(jsonDatabase, index);
                    break;
                case "set":
                    index = Integer.parseInt(scanner.next("\\d+"));
                    var value = scanner.nextLine().trim();
                    result = new CommandSet(jsonDatabase, index, value);
                    break;
                case "delete":
                    index = Integer.parseInt(scanner.nextLine().trim());
                    result = new CommandDelete(jsonDatabase, index);
                    break;
                case "exit":
                    result = new CommandExit();
                    break;
                default:
                    result = new CommandError();
                    break;
            }
        } catch (Exception ignored) {
            result = new CommandError();
        }
        return result;
    }
}
