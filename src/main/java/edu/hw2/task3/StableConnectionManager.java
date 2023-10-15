package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.StableConnection;

public class StableConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return new StableConnection();
    }
}
