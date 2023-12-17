package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private final ExecutorService executor;
    private final int maxConnections;
    private ServerSocket server;

    public Server(int port, int maxConnections) {
        executor = Executors.newFixedThreadPool(maxConnections);
        this.maxConnections = maxConnections;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void innerRun() {
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
        } catch (SocketException ignore) {
        } catch (IOException | ExecutionException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        executor.shutdown();
    }

    public void run() {
        Thread runThread = new Thread(this::innerRun);
        runThread.start();
    }

    public  void close()  {
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
