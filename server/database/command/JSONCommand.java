package server.database.command;

import server.database.JSONDatabase;

public abstract class JSONCommand implements Command {
    protected final JSONDatabase jsonDatabase;
    protected JSONCommand (JSONDatabase database) {
        this.jsonDatabase = database;
    }
}
