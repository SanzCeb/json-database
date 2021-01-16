package server.database.command;

import server.database.JsonDatabaseResponse;

import java.util.concurrent.Callable;

public interface JsonDatabaseCommand extends Callable<JsonDatabaseResponse> {
}
