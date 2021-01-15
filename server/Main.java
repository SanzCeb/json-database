package server;

import com.google.gson.GsonBuilder;
import server.database.JSONDatabase;
import server.database.JSONDatabaseArgs;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;
        var jsonDatabase = new JSONDatabase();
        var commandFactory = jsonDatabase.commandFactory();
        var gson = new GsonBuilder().create();
        System.out.println("Server started!");
        try (var serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            while (true) {
                try (
                        var socket = serverSocket.accept();
                        var input = new DataInputStream(socket.getInputStream());
                        var output = new DataOutputStream(socket.getOutputStream())
                ) {
                    var receivedInput = input.readUTF();
                    var commandArgs = gson.fromJson(receivedInput, JSONDatabaseArgs.class);
                    commandFactory.parseCommand(commandArgs).execute();
                    var outputMsg = gson.toJson(jsonDatabase.getCommandResult());
                    output.writeUTF(outputMsg);
                } catch (Exception ignored){ break;}
            }
        } catch (Exception ignored) {}
    }
}

