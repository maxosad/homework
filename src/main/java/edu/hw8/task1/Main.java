package edu.hw8.task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int port = 3435;
        Server server = new Server(port, 2, maxConnections);
        Client client = new Client(port);
        Thread t1 = new Thread(() -> server.run());
        Thread t2 = new Thread(() -> System.out.println(client.send("интеллект")));

        t1.start();
        Thread.sleep(1000);
        t2.start();

        t2.join();
        t1.join();
    }
}
