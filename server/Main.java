package server;

import com.beust.jcommander.JCommander;
import server.database.JSONDatabase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;
        var jsonDatabase = new JSONDatabase();
        var commandFactory = jsonDatabase.commandFactory();
        System.out.println("Server started!");
        try (var serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            while (true) {
                try (
                        var socket = serverSocket.accept();
                        var input = new DataInputStream(socket.getInputStream());
                        var output = new DataOutputStream(socket.getOutputStream())
                ) {
                    var receivedInput = input.readUTF();
                    commandFactory.parseCommand(receivedInput).execute();
                    var outputMsg = jsonDatabase.getCommandResult();
                    output.writeUTF(outputMsg);
                }
            }
        } catch (Exception ignored) {
        }
    }
}

