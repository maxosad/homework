package edu.hw1;

import org.apache.logging.log4j.LogManager;

public class Task0 {
    public static void printHelloWorld() {
        var logger = LogManager.getLogger();
        logger.info("Привет, мир!");
    }

    public static void main(String[] args) {
        printHelloWorld();
    }
}
