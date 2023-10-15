package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import edu.hw2.task3.connectionManagers.ConnectionManager;
import edu.hw2.task3.connectionManagers.DefaultConnectionManager;
import edu.hw2.task3.connectionManagers.FaultyConnectionManager;
import edu.hw2.task3.connectionManagers.StableConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionManagerTest {

    @Test
    @DisplayName("should return instanceof FaultyConnection")
    void faultyConnectionManager() {
        ConnectionManager faultyConnectionManager = new FaultyConnectionManager();
        assertTrue(faultyConnectionManager.getConnection() instanceof FaultyConnection);
    }

    @Test
    @DisplayName("should return instanceof StableConnection")
    void stableConnectionManager() {
        ConnectionManager stableConnectionManager = new StableConnectionManager();
        assertTrue(stableConnectionManager.getConnection() instanceof StableConnection);
    }

    @Test
    @DisplayName("should return FaultyConnections and StableConnections")
    void defaultConnectionManager() {
        ConnectionManager defaultConnectionManager = new DefaultConnectionManager();
        List<Connection> connections = new ArrayList<>();
        for (int connectionId = 0; connectionId < 20; connectionId++) {
            connections.add(defaultConnectionManager.getConnection());
        }

        boolean isFaulty = false;
        boolean isStable = false;

        for (Connection connection : connections) {
            if (connection instanceof FaultyConnection) {
                isFaulty = true;
            }

            if (connection instanceof StableConnection) {
                isStable = true;
            }
        }

        assertTrue(isFaulty);
        assertTrue(isStable);



    }
}
