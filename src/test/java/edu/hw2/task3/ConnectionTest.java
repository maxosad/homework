package edu.hw2.task3;

import edu.hw2.task3.connection.BasicConnection;
import edu.hw2.task3.connection.ConnectionException;
import edu.hw2.task3.connection.ConnectionStatus;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.FullFaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {

    private static List<BasicConnection> connections;

    private boolean isOpened(BasicConnection basicConnection) {
        return basicConnection.getConnectionStatus().equals(ConnectionStatus.OPEND);
    }

    private boolean isClosed(BasicConnection basicConnection) {
        return basicConnection.getConnectionStatus().equals(ConnectionStatus.CLOSED);
    }

    @BeforeEach
    void setUp() {
        connections = new ArrayList<>();
        connections.add(new BasicConnection());
        connections.add(new FaultyConnection());
        connections.add(new FullFaultyConnection());
        connections.add(new StableConnection());
    }

    @Test
    void initTest() {
        for (var connection : connections) {
            assertTrue(isOpened(connection));
        }
    }

    @Test
    void closeTest() {
        try {
            for (var connection : connections) {
                connection.close();
                assertTrue(isClosed(connection));
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void doubleСloseTest() {
        try {
            for (var connection : connections) {
                connection.close();
                assertTrue(isClosed(connection));
            }
        } catch (Exception e) {
            fail();
        }
        for (var connection : connections) {
            assertThrows(ConnectionException.class, connection::close);
        }
    }

    @Test
    void execute() {
    }
}
