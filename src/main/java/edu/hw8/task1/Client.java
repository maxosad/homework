package edu.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private int port;

    public Client(int port) {
        this.port = port;
    }

    public String send(String messageToSend) {
        String ans = "";
        try (Socket client = new Socket("localhost", port)) {
            try (DataOutputStream out = new DataOutputStream(client.getOutputStream());
                 DataInputStream in = new DataInputStream(client.getInputStream())) {
                out.writeUTF(messageToSend);
                ans = in.readUTF();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ans;
    }
}
