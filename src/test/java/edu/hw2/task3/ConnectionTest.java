package edu.hw2.task3;

import edu.hw2.task3.connection.BasicConnection;
import edu.hw2.task3.connection.ConnectionException;
import edu.hw2.task3.connection.ConnectionStatus;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.FullFaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static edu.hw2.task3.connection.BasicConnection.CLOSE_CLOSED_MESSAGE;
import static edu.hw2.task3.connection.BasicConnection.EXECUTE_CLOSED_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {

    private static final String DOUBLE_CLOSE_MESSAGE = "Connection was already closed, but we tried to close it again";

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

    void closeConnections() {
        for (var connection : connections) {
            try {
                connection.close();
            } catch (Exception ignore) {}
        }
    }

    @Test
    void initTest() {
        for (var connection : connections) {
            assertTrue(isOpened(connection));
        }
        closeConnections();
    }

    @Test
    void closeTest() {
        try {
            for (var connection : connections) {
                connection.close();
                assertTrue(isClosed(connection));
            }
        } catch (Exception e) {
            fail(DOUBLE_CLOSE_MESSAGE);
        }
    }

    @Test
    @DisplayName("Should throw ConnectionException")
    void doubleСloseTest() {
        try {
            for (var connection : connections) {
                connection.close();
                assertTrue(isClosed(connection));
            }
        } catch (Exception e) {
            fail(DOUBLE_CLOSE_MESSAGE);
        }

        for (var connection : connections) {
            try {
                connection.close();
            } catch (Exception e) {
                assertEquals(CLOSE_CLOSED_MESSAGE, e.getMessage());
                assertTrue(e instanceof ConnectionException);
                return;
            }
            fail("test should end in catch block");
        }
    }

    @Test
    @DisplayName("Should throw ConnectionException with message")
    void executeOnClosedTest() {
        try {
            for (var connection : connections) {
                connection.close();
                assertTrue(isClosed(connection));
            }
        } catch (Exception e) {
            fail(DOUBLE_CLOSE_MESSAGE);
        }

        for (var connection : connections) {
            try {
                connection.execute("testMessage");
            } catch (Exception e) {
                assertEquals(EXECUTE_CLOSED_MESSAGE, e.getMessage());
                assertTrue(e instanceof ConnectionException);
                return;
            }
            fail("test should end in catch block");
        }
    }

    @Test
    @DisplayName("Should throw ConnectionException")
    void fullFaulty() {
        try (FullFaultyConnection fullFaultyConnection = new FullFaultyConnection()) {
        String testMessage = "testMessage";
        assertThrows(ConnectionException.class, () -> fullFaultyConnection.execute(testMessage));
        } catch (Exception ignored) { }
    }

    @Test
    @DisplayName("Should not throw ConnectionException")
    void stable() {
        try (StableConnection stableConnection = new StableConnection()) {
            String testMessage = "testMessage";
            assertDoesNotThrow(() -> stableConnection.execute(testMessage));
        } catch (Exception ignored) { }
    }
}
