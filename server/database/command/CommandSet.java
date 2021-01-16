package server.database.command;

import server.database.JSONDatabase;
import server.database.JsonDatabaseResponse;

public class CommandSet extends JsonDatabaseCommandImpl {
    private final String key;
    private final String data;

    CommandSet(JSONDatabase database, String key, String data) {
        super(database);
        this.key = key;
        this.data = data;
    }

    @Override
    public JsonDatabaseResponse call() throws Exception {
        return jsonDatabase.set(key, data);
    }
}
