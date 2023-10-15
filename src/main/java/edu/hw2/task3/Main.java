package edu.hw2.task3;

import edu.hw2.task3.connectionManagers.ConnectionManager;
import edu.hw2.task3.connectionManagers.DefaultConnectionManager;
import edu.hw2.task3.connectionManagers.FaultyConnectionManager;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ConnectionManager faulty = new FaultyConnectionManager();
        ConnectionManager def = new DefaultConnectionManager();
        int maxAttempts = 1;
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(faulty, maxAttempts);

        popularCommandExecutor.updatePackages();
    }
}
