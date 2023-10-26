package edu.project2;

import java.util.HashMap;
import java.util.Map;

public class Cell {
    private Map<Side, Boolean> walls;
    private int row;
    private int col;
    public enum Side { TOP, LEFT, RIGHT, DOWN};

    public Cell( int row, int col) {
        this.row = row;
        this.col = col;
        walls = new HashMap<>();
        walls.put(Side.TOP, false);
        walls.put(Side.LEFT, false);
        walls.put(Side.RIGHT, false);
        walls.put(Side.DOWN, false);
    }

    public Map<Side, Boolean> getWalls() {
        return walls;
    }
}
