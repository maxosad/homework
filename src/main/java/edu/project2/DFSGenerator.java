package edu.project2;

import java.util.Random;

public class DFSGenerator implements Generator {
    private int height;
    private int width;
    private Cell[][] grid;
    private boolean used[][];
    private Random random;

    private void dfs(int row, int col) {
        used[row][col] = true;

    }
    @Override
    public Maze generate(int height, int width, int seed) {
        random = new Random(seed);
        used = new boolean[height][width];
        grid = new Cell[height][width];
        dfs(1, 1);
        return null;
    }
}
