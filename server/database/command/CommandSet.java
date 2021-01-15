package server.database.command;

import server.database.JSONDatabase;

public class CommandSet extends JSONCommand {
    private final String key;
    private final String data;

    CommandSet(JSONDatabase database, String key, String data) {
        super(database);
        this.key = key;
        this.data = data;
    }

    @Override
    public void execute() {
        jsonDatabase.set(key, data);
    }
}
