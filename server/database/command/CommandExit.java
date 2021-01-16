package server.database.command;


import server.database.JsonDatabaseResponse;

public class CommandExit implements JsonDatabaseCommand {
    @Override
    public JsonDatabaseResponse call() throws Exception {
        return null;
    }
}
