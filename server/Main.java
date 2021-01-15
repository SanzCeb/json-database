package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;
        System.out.println("Server started!");
        try (var serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address));
             var socket = serverSocket.accept();
             var input = new DataInputStream(socket.getInputStream());
             var output = new DataOutputStream(socket.getOutputStream())) {
            var receivedInput = input.readUTF();
            System.out.printf("Received: %s%n", receivedInput);
            var scanner = new Scanner(receivedInput);
            var record = scanner.findInLine("\\d+");
            var outputMsg = String.format("A record # %s was sent!", record);
            System.out.printf("Sent: %s%n", outputMsg);
            output.writeUTF(outputMsg);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

