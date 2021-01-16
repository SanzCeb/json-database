package server;

import com.google.gson.GsonBuilder;
import server.database.JSONDatabase;
import server.database.args.JSONDatabaseArgs;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;
        var jsonDatabase = new JSONDatabase();
        var commandFactory = jsonDatabase.commandFactory();
        System.out.println("Server started!");
        var executorService = createExecutorService();
        try (var serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            while (true) {
                try (
                        var socket = serverSocket.accept();
                        var input = new DataInputStream(socket.getInputStream());
                        var output = new DataOutputStream(socket.getOutputStream())
                ) {
                    var receivedInput = input.readUTF();
                    var commandArgs = JSONDatabaseArgs.unmarshalling(receivedInput);
                    var jsonDatabaseCommand = commandFactory.parseCommand(commandArgs);
                    var jsonCommandResponse = CommandExecutor.runCommand(jsonDatabaseCommand);
                    var outputMsg = jsonCommandResponse.marshalling();
                    output.writeUTF(outputMsg);
                } catch (InterruptedException exception){
                    executorService.stop();
                    System.exit(0);
                }
            }
        } catch (Exception ignored) {}
    }

    private static CommandExecutor createExecutorService() {
        return new CommandExecutor();
    }
}

