package client;

import com.google.gson.GsonBuilder;
import server.database.args.JSONDatabaseArgs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Main {

    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;
        JSONDatabaseArgs jsonDatabaseArgs = JSONDatabaseArgs.parseCmdLineArgs(args);

        try (var socket = new Socket(InetAddress.getByName(address), port);
             var input = new DataInputStream(socket.getInputStream());
             var output = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Client started!");
            var jsonDBArgsString = JSONDatabaseArgs.marshalling(jsonDatabaseArgs);
            System.out.printf("Sent: %s%n", jsonDBArgsString);
            output.writeUTF(jsonDBArgsString);
            if (!jsonDBArgsString.contains("exit")) {
                System.out.printf("Received: %s%n", input.readUTF());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
