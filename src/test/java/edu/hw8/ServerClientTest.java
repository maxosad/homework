package edu.hw8;

import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ServerClientTest {

    @Test
    void serverTest()  {
        int port = 3435;
        String ans = null;

        try {
            Server server = new Server(port, 3);
            Client client = new Client(port);
            server.run();
            ans = client.send("интеллект");
            Thread.sleep(1000);
            server.close();
        } catch (Exception e) {
            System.out.println("erftyjk");
            fail(e);
        }

        assertEquals("Чем ниже интеллект, тем громче оскорбления", ans);
    }


    @Test
    void manyClients() {
        int port = 3435;
        int maxConnections = 3;
        List<Runnable> tasks = new ArrayList<>();

        try (ExecutorService executor =
                 Executors.newFixedThreadPool(maxConnections);) {
            Server server = new Server(port, maxConnections);

            for (int clientIndex = 0; clientIndex < 10; clientIndex++) {
                tasks.add(() -> {
                    Client client = new Client(port);
                    String ans = client.send("интеллект");
                    assertEquals("Чем ниже интеллект, тем громче оскорбления", ans);
                });
            }

            server.run();

            tasks.forEach(executor::submit);

            Thread.sleep(1000);
            server.close();
        } catch (Exception e) {
            fail(e);
        }
    }
}
