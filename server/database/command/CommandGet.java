package server.database.command;

import server.database.JSONDatabase;

public class CommandGet extends JSONCommand {
    private final String key;

    CommandGet(JSONDatabase database, String index) {
        super(database);
        this.key = index;
    }

    @Override
    public void execute() {
        jsonDatabase.get(key);
    }
}
