package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private final ExecutorService executor;
    private int port;
    private final int maxConnections;
    private boolean running;
    private ServerSocket server;

    public Server(int port, int nThreads) throws IOException {
        executor = Executors.newFixedThreadPool(nThreads);
        this.port = port;
        this.maxConnections = nThreads;
        running = false;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void run() {
        try {
            AtomicInteger clientNumber = new AtomicInteger(0);
            while (!server.isClosed()) {
                if (clientNumber.incrementAndGet() <= maxConnections) {
                    Socket client = server.accept();
                    Future<?> isEnded = executor.submit(new MonoThreadClientHandler(client));
                    isEnded.get();
                    clientNumber.decrementAndGet();
                }
            }
        } catch (IOException | ExecutionException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        executor.shutdown();
    }

    public void stop()  {
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




//    public static void main(String[] args) {
//        Server server = new Server(3345, 1);
//        server.run();
//    }
}
