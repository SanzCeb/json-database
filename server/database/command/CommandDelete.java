package server.database.command;

import server.database.JSONDatabase;

public class CommandDelete extends JSONCommand {
    private final int index;

    public CommandDelete(JSONDatabase database, int index) {
        super(database);
        this.index = index;
    }

    @Override
    public void execute() {
        boolean result = jsonDatabase.delete(index);
        System.out.println(result ? "OK" : "ERROR");
    }
}
