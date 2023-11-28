package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final ExecutorService executor;
    private int port;
    private final int maxConnections;

    public Server(int port, int nThreads, int maxConnections) {
        executor = Executors.newFixedThreadPool(nThreads);
        this.port = port;
        this.maxConnections = maxConnections;
    }

    public void run() {
        try(ServerSocket server = new ServerSocket(port)) {
            int clientNumber = 0;

            while (!server.isClosed() && clientNumber < maxConnections) {
                Socket client = server.accept();
                executor.execute(new MonoThreadClientHandler(client));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
         executor.shutdown();

    }

    public static void main(String[] args) {
        Server server = new Server(3345, 1, 5);
        server.run();

    }
}
