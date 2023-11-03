package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conf {
    public static final int MAX_ATTEMPTS = 5;
    public static final Logger LOGGER = LogManager.getLogger();

    private Conf() { }
}
