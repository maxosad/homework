package edu.project2.solvers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelDFSSolver implements Solver {
    public static final int[][] SDVIG = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
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

        DFSRecursiveTask recursiveTask = new DFSRecursiveTask(start, end, grid, usedGrid);
        try (ForkJoinPool fj = new ForkJoinPool()) {
            var entry = fj.invoke(recursiveTask);


            boolean solutionFound = entry.getValue();
            if (!solutionFound) {
                throw new RuntimeException("No path between start and end");
            }
            path = entry.getKey();
        }
        return path.stream().toList();
    }
}
