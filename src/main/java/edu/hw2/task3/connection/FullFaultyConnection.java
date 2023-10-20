package edu.hw2.task3.connection;

public class FullFaultyConnection extends BasicConnection {

    @Override
    public void execute(String command) {
        throw new ConnectionException();
    }
}
