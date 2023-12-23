package edu.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import lombok.SneakyThrows;

public class Client {
    private int port;

    public Client(int port) {
        this.port = port;
    }

    @SneakyThrows
    public String send(String messageToSend) {
        try (Socket client = new Socket("localhost", port);
             DataOutputStream out = new DataOutputStream(client.getOutputStream());
             DataInputStream in = new DataInputStream(client.getInputStream())) {
            out.writeUTF(messageToSend);
            return in.readUTF();
        }
    }
}
