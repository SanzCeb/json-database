package server.database.command;

import server.database.JSONDatabase;
import server.database.JsonDatabaseResponse;

public class CommandGet extends JsonDatabaseCommandImpl {
    private final String key;

    CommandGet(JSONDatabase database, String key) {
        super(database);
        this.key = key;
    }

    @Override
    public JsonDatabaseResponse call() throws Exception {
        return jsonDatabase.get(key);
    }
}
