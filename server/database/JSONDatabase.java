package server.database;

import server.database.command.CommandFactory;

import java.util.HashMap;
import java.util.Map;

public class JSONDatabase {
    private JSONDatabaseResponse result;
    private final Map <String, String> cells = new HashMap<>();

    public void set (String key, String value) {
        cells.put(key, value);
        result = JSONDatabaseResponse.ok();

    }

    public void get (String key) {
        var value = cells.getOrDefault(key, "");
        result = !value.isEmpty() ? JSONDatabaseResponse.responseWithValue(value)
                : JSONDatabaseResponse.noSuchKey();
    }

    public void delete (String key) {
        var value = cells.getOrDefault(key, "");
        if (!value.isEmpty()) {
            set(key, "");
            result = JSONDatabaseResponse.ok();
        } else {
            result = JSONDatabaseResponse.noSuchKey();
        }
    }

    public CommandFactory commandFactory () {
        return new CommandFactory(this);
    }

    public JSONDatabaseResponse getCommandResult() {
        return result;
    }
}
