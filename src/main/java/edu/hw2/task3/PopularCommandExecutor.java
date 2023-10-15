package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.ConnectionException;
import edu.hw2.task3.connectionManagers.ConnectionManager;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        Connection connection = manager.getConnection();
        int currentAttempt = 0;
        boolean executed = false;
        Throwable cause = null;

        while (currentAttempt <= maxAttempts) {
            try {
                currentAttempt++;
                connection.execute(command);
                executed = true;
                break;
            } catch (ConnectionException e) {
                cause = e;
            }
        }

        if (!executed) {
            throw new ConnectionException(cause);
        }
    }
}
