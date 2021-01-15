package server.database;

import server.database.command.Command;
import server.database.command.CommandFactory;
import java.util.Optional;
import java.util.stream.Stream;

public class JSONDatabase {
    private final String [] cells = Stream.generate(() -> "")
            .limit(100)
            .toArray(String[]::new);

    public boolean set (int index, String cell) {
        boolean result;

        if (index < 1 || index > 100) {
            result = false;
        } else {
            cells[index - 1] = cell;
            result = true;
        }

        return result;
    }

    public Optional<String> get (int index) {
        return Optional.of(index)
                .filter(i -> i > 0 && i < 101)
                .map(i -> cells[i - 1])
                .filter(cell -> !cell.isEmpty());
    }

    public boolean delete (int index) {
        return set(index, "");
    }

    public CommandFactory commandFactory () {
        return new CommandFactory(this);
    }

}
