package edu.hw2.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    public static void callCollingInfo() {
        LOGGER.info(CollingInfoFunction.callingInfo());
    }
}
