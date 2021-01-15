package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;
        try (var socket = new Socket(InetAddress.getByName(address), port);
             var input = new DataInputStream(socket.getInputStream());
             var output = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("Client started!");
            var sentMsg = "Give me a record # 12";
            System.out.printf("Sent: %s%n", sentMsg);
            output.writeUTF(sentMsg);
            System.out.printf("Received: %s%n", input.readUTF());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
