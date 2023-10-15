package edu.hw2.task3;

import edu.hw2.task3.connection.ConnectionException;
import edu.hw2.task3.connectionManagers.ConnectionManager;
import edu.hw2.task3.connectionManagers.DefaultConnectionManager;
import edu.hw2.task3.connectionManagers.FullFaultyManager;
import edu.hw2.task3.connectionManagers.StableConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopularCommandExecutorTest {

    @Test
    @DisplayName("fullFaultyManager should throw ConnectionException")
    void tryExecute1() {
        FullFaultyManager fullFaultyManager = new FullFaultyManager();
        int attempts = 10;
        String testMessage = "testMessage";

        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(fullFaultyManager, attempts);

        assertThrows(ConnectionException.class, () -> popularCommandExecutor.tryExecute(testMessage));

    }

    @Test
    @DisplayName("fullFaultyManager should throw ConnectionException with cause ConnectionException")
    void tryExecute2() {
        FullFaultyManager fullFaultyManager = new FullFaultyManager();
        int attempts = 10;
        String testMessage = "testMessage";

        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(fullFaultyManager, attempts);

        try {
            popularCommandExecutor.tryExecute(testMessage);
            fail();
        } catch (ConnectionException e) {
            assertTrue(e.getCause() instanceof ConnectionException);
        }
    }

    @Test
    @DisplayName("should not throw ConnectionException")
    void tryExecuteSuccess() {
        ConnectionManager stableConnectionManager = new StableConnectionManager();
        int attempts = 10;
        String testMessage = "testMessage";

        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(stableConnectionManager, attempts);

        assertDoesNotThrow(() -> popularCommandExecutor.tryExecute(testMessage));
    }
}
