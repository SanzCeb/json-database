package server.database.command;

import server.database.JSONDatabase;

public class CommandGet extends JSONCommand {
    private final int index;

    protected CommandGet(JSONDatabase database, int index) {
        super(database);
        this.index = index;
    }

    @Override
    public void execute() {
        jsonDatabase.get(index)
                .ifPresentOrElse(System.out::println
                        ,() -> System.out.println("ERROR"));
    }
}
