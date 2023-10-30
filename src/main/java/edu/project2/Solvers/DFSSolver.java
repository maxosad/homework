package edu.project2.Solvers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import static edu.project2.Config.DIRECTIONS_COUNT;

public class DFSSolver implements Solver {
    private static final int[][] SDVIG = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    private Coordinate end;
    private Cell[][] grid;
    private boolean[][] usedGrid;
    private int height;
    private int width;
    private Deque<Coordinate> path;

    private boolean dfs(Coordinate c) {
        usedGrid[c.row()][c.col()] = true;
        if (c.equals(end)) {
            path.addFirst(c);
            return true;
        }
        for (int sdvid = 0; sdvid < DIRECTIONS_COUNT; sdvid++) {
            Coordinate newC = new Coordinate(c.row() + SDVIG[sdvid][0], c.col() + SDVIG[sdvid][1]);
            if (0 <= newC.col() && newC.col() < width
                && 0 <= newC.row() && newC.row() < height
                && !usedGrid[newC.row()][newC.col()]
                && grid[newC.row()][newC.col()].getType().equals(Cell.Type.PASSAGE)) {
                if (dfs(newC)) {
                    path.addFirst(c);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        this.end = end;
        grid = maze.getGrid();
        height = maze.getHeight();
        width = maze.getWidth();
        usedGrid = new boolean[height][width];
        path = new ArrayDeque<>();

        boolean solutionFound = dfs(start);
        if (!solutionFound) {
            throw new RuntimeException("No path");
        }
        return path.stream().toList();
    }
}
