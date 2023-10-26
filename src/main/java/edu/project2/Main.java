package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
//        LOGGER.info(" — —");
//        LOGGER.info("|   |");
//        LOGGER.info(" —  ");
        Cell cell = new Cell(2, 2);
        Map<Cell.Side, Boolean> m = cell.getWalls();
        m.put(Cell.Side.TOP, true);
        var m1 = cell.getWalls();
        LOGGER.info(m1.get(Cell.Side.TOP));
    }
}
