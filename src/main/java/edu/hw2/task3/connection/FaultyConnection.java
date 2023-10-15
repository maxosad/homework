package edu.hw2.task3.connection;

import java.util.Random;

public class FaultyConnection extends BasicConnection {
    private final Random random = new Random(1);
    @Override
    public void execute(String command) {
        if (random.nextInt(2) == 0) {
            throw new ConnectionException();
        } else {
            super.execute(command);
        }
    }
}
