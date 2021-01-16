package server.database.command;


import server.database.JsonDatabaseResponse;

public class CommandError implements JsonDatabaseCommand {
    @Override
    public JsonDatabaseResponse call() throws Exception {
        System.out.println("Invalid Command");
        return null;
    }
}
