package edu.project2.solvers;

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
            throw new RuntimeException("No path between start and end");
        }
        return path.stream().toList();
    }

    private boolean dfs(Coordinate coordinate) {
        usedGrid[coordinate.row()][coordinate.col()] = true;
        if (coordinate.equals(end)) {
            path.addFirst(coordinate);
            return true;
        }
        for (int sdvigIndex = 0; sdvigIndex < DIRECTIONS_COUNT; sdvigIndex++) {
            Coordinate newCoordinate = new Coordinate(
                coordinate.row() + SDVIG[sdvigIndex][0], coordinate.col() + SDVIG[sdvigIndex][1]);
            if (0 <= newCoordinate.col() && newCoordinate.col() < width
                && 0 <= newCoordinate.row() && newCoordinate.row() < height
                && !usedGrid[newCoordinate.row()][newCoordinate.col()]
                && grid[newCoordinate.row()][newCoordinate.col()].getType().equals(Cell.Type.PASSAGE)
                && dfs(newCoordinate)) {
                    path.addFirst(coordinate);
                    return true;
            }
        }
        return false;
    }

}
