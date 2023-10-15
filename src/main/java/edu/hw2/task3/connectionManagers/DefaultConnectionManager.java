package edu.hw2.task3.connectionManagers;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random random = new Random(0);

    @Override
    public Connection getConnection() {
        if (random.nextInt(2) == 0) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
