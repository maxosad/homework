package edu.project2;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.width = width;
        this.height = height;
        grid = new Cell[width][height];
    }
}
