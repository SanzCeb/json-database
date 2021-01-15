package server.database.command;

import server.database.JSONDatabase;

public class CommandDelete extends JSONCommand {
    private final String key;

    CommandDelete(JSONDatabase database, String key) {
        super(database);
        this.key = key;
    }

    @Override
    public void execute() {
        jsonDatabase.delete(key);
    }

}
