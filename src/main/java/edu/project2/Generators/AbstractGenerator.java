package edu.project2.Generators;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public abstract class AbstractGenerator implements Generator {
    protected static final int[][] SDVIG = new int[][] {{0, -2}, {0, 2}, {2, 0}, {-2, 0}};
    protected int width;
    protected int height;
    Random random;
    protected Cell[][] grid;
    protected HashMap<Coordinate, Boolean> usedPretend;

    protected void fillGridWithWalls() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
    }

    protected Coordinate between(Coordinate c1, Coordinate c2) {
        return new Coordinate((c1.row() + c2.row()) / 2, (c1.col() + c2.col()) / 2);
    }

    protected void prepare(int height, int width, int seed) {
        this.height = height;
        this.width = width;
        random = new Random(seed);
        grid = new Cell[height][width];
        usedPretend = new HashMap<>();
        fillGridWithWalls();
    }

}
