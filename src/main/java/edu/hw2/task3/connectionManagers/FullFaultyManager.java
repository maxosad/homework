package edu.hw2.task3.connectionManagers;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FullFaultyConnection;

public class FullFaultyManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return new FullFaultyConnection();
    }
}
