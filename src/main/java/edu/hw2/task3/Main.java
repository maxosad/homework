package edu.hw2.task3;

import edu.hw2.task3.connectionManagers.ConnectionManager;
import edu.hw2.task3.connectionManagers.DefaultConnectionManager;
import edu.hw2.task3.connectionManagers.FaultyConnectionManager;
import edu.hw2.task3.connectionManagers.FullFaultyManager;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ConnectionManager faulty = new FaultyConnectionManager();
        ConnectionManager fullFaulty = new FullFaultyManager();

        ConnectionManager def = new DefaultConnectionManager();
        int maxAttempts = 2;
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(fullFaulty, maxAttempts);
        popularCommandExecutor.updatePackages();
    }
}
