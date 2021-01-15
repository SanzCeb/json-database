package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    @Parameter(names="-t", description = "Type of command")
    private String commandType;
    @Parameter (names="-i", description = "Index of the record")
    private Integer index;
    @Parameter (names = "-m", description = "Record content")
    private String record;

    public static void main(String[] args) {
        var main = new Main();
        String address = "127.0.0.1";
        int port = 23456;
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        try (var socket = new Socket(InetAddress.getByName(address), port);
             var input = new DataInputStream(socket.getInputStream());
             var output = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Client started!");
            String sentMsg = Stream.of(main.commandType, main.index, main.record)
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.joining(" "));
            System.out.printf("Sent: %s%n", sentMsg);
            output.writeUTF(sentMsg);
            if (!sentMsg.contains("exit")) {
                System.out.printf("Received: %s%n", input.readUTF());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
