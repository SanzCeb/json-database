package server.database;

import server.database.command.CommandFactory;
import server.database.file.JsonFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JSONDatabase {

    private HashMap <String, String> cells = JSON_FILE.read();
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    Lock readLock = rwl.readLock();
    Lock writeLock = rwl.writeLock();
    private static final JsonFile JSON_FILE = new JsonFile();

    public JsonDatabaseResponse set (String key, String value) {
        writeLock.lock();
        try {
            cells.put(key, value);
            JSON_FILE.write(cells);
            return JsonDatabaseResponse.ok();
        } finally {
            writeLock.unlock();
        }
    }

    public JsonDatabaseResponse get (String key) {
        readLock.lock();
        try {
            cells = JSON_FILE.read();
            var value = cells.getOrDefault(key, "");
            return !value.isEmpty() ? JsonDatabaseResponse.responseWithValue(value)
                    : JsonDatabaseResponse.noSuchKey();
        } finally {
            readLock.unlock();
        }
    }

    public JsonDatabaseResponse delete (String key) {
        var value = get(key).getResponse();
        return value.equals(JsonDatabaseResponse.ok().getResponse()) ? set(key, "") : JsonDatabaseResponse.noSuchKey();
    }

    public CommandFactory commandFactory () {
        return new CommandFactory(this);
    }

}
