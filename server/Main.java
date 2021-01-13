package server;

import server.database.JSONDatabase;

public class Main {

    public static void main(String[] args) {
        var database = new JSONDatabase();
        var commandFactory = database.commandFactory();
        var scanner = new java.util.Scanner(System.in);
        while (true) {
            var userInput = scanner.nextLine();
            var command = commandFactory.parseCommand(userInput);
            command.execute();
        }
    }
}
