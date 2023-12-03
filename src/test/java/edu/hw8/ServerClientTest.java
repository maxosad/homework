package edu.hw8;

import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import org.junit.jupiter.api.Test;
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
}
