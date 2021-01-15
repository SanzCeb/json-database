package server.database.command;

import server.database.JSONDatabase;

public class CommandDelete extends JSONCommand {
    private final int index;

    CommandDelete(JSONDatabase database, int index) {
        super(database);
        this.index = index;
    }

    @Override
    public void execute() {
        jsonDatabase.delete(index);
    }

}
