package server.database.command;

import server.database.JSONDatabase;

public abstract class JsonDatabaseCommandImpl implements JsonDatabaseCommand {
    protected final JSONDatabase jsonDatabase;
    protected JsonDatabaseCommandImpl(JSONDatabase database) {
        this.jsonDatabase = database;
    }
}
