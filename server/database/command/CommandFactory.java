package server.database.command;

import server.database.JSONDatabase;
import server.database.args.JSONDatabaseArgs;

public class CommandFactory {
    private final JSONDatabase jsonDatabase;

    public CommandFactory(JSONDatabase jsonDatabase) {
        this.jsonDatabase = jsonDatabase;
    }

    public JsonDatabaseCommand parseCommand(JSONDatabaseArgs commandArgs) {
        JsonDatabaseCommand result;
        try {
            var commandName = commandArgs.getType();
            String key;
            switch (commandName.toLowerCase()) {
                case "get":
                    key = commandArgs.getKey();
                    result = new CommandGet(jsonDatabase, key);
                    break;
                case "set":
                    key = commandArgs.getKey();
                    var value = commandArgs.getValue();
                    result = new CommandSet(jsonDatabase, key, value);
                    break;
                case "delete":
                    key = commandArgs.getKey();
                    result = new CommandDelete(jsonDatabase, key);
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
