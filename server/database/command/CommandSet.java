package server.database.command;

import server.database.JSONDatabase;

public class CommandSet extends JSONCommand {
    private final int index;
    private final String data;

    CommandSet(JSONDatabase database, int index, String data) {
        super(database);
        this.index = index;
        this.data = data;
    }

    @Override
    public void execute() {
        jsonDatabase.set(index, data);
    }
}
