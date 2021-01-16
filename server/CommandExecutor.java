package server;

import server.database.JsonDatabaseResponse;
import server.database.command.CommandExit;
import server.database.command.JsonDatabaseCommand;

import java.util.concurrent.*;


public class CommandExecutor {
    private static final ExecutorService executorService;
    private static final long TIME_OUT_MILLIS = 1000L;

    static {
        int poolSize = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    public static JsonDatabaseResponse runCommand (JsonDatabaseCommand jsonDatabaseCommand) throws Exception {
        try {
            if (jsonDatabaseCommand instanceof CommandExit) {
                throw new InterruptedException();
            }
            var future = executorService.submit(jsonDatabaseCommand::call);
            return future.get(TIME_OUT_MILLIS, TimeUnit.MILLISECONDS);
        }catch (ExecutionException | TimeoutException ignored){
            return null;
        }
    }

    public static void stop () {
         executorService.shutdown();
        while (!executorService.isTerminated());
    }
}
