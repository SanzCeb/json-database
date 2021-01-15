package server.database;

import server.database.command.CommandFactory;

import java.util.Optional;
import java.util.stream.Stream;

public class JSONDatabase {
    private String result = "ERROR";
    private final String [] cells = Stream.generate(() -> "")
            .limit(1000)
            .toArray(String[]::new);

    public void set (int index, String cell) {

        if (index < 1 || index > 100) {
            setCommandResult("ERROR");
        } else {
            cells[index - 1] = cell;
            setCommandResult("OK");
        }
    }

    public void get (int index) {
        Optional.of(index)
                .filter(i -> i > 0 && i < 101)
                .map(i -> cells[i - 1])
                .filter(cell -> !cell.isEmpty())
                .ifPresentOrElse(this::setCommandResult, () -> setCommandResult("ERROR"));
    }

    public void delete (int index) {
        set(index, "");
    }

    public CommandFactory commandFactory () {
        return new CommandFactory(this);
    }

    private void setCommandResult(String result) {
        this.result = result;
    }

    public String getCommandResult() {
        return result;
    }
}
