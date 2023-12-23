package edu.project2.solvers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import static edu.project2.Config.DIRECTIONS_COUNT;
import static edu.project2.solvers.ParallelDFSSolver.SDVIG;

public class DFSRecursiveTask extends RecursiveTask<AbstractMap.SimpleEntry<Deque<Coordinate>, Boolean>> {
    private Cell[][] grid;
    private boolean[][] usedGrid;
    private Coordinate coordinate;
    private Coordinate end;

    public DFSRecursiveTask(Coordinate currentCoordinate, Coordinate end, Cell[][] grid, boolean[][] usedGrid) {
        this.coordinate = currentCoordinate;
        this.end = end;
        this.grid = grid;
        this.usedGrid = usedGrid;
    }

    @Override
    protected AbstractMap.SimpleEntry<Deque<Coordinate>, Boolean> compute() {
        Deque<Coordinate> path = new ArrayDeque<>();
        usedGrid[coordinate.row()][coordinate.col()] = true;
        if (coordinate.equals(end)) {
            path.addFirst(end);
            return new AbstractMap.SimpleEntry<>(path, true);
        } else {
            int height = usedGrid.length;
            int width = usedGrid[0].length;
            List<DFSRecursiveTask> recursiveTasks = new ArrayList<>();
            for (int sdvigIndex = 0; sdvigIndex < DIRECTIONS_COUNT; sdvigIndex++) {
                Coordinate newCoordinate = new Coordinate(
                    coordinate.row() + SDVIG[sdvigIndex][0], coordinate.col() + SDVIG[sdvigIndex][1]);
                if (0 <= newCoordinate.col() && newCoordinate.col() < width
                    && 0 <= newCoordinate.row() && newCoordinate.row() < height
                    && !usedGrid[newCoordinate.row()][newCoordinate.col()]
                    && grid[newCoordinate.row()][newCoordinate.col()].getType().equals(Cell.Type.PASSAGE)) {
                    recursiveTasks.add(new DFSRecursiveTask(newCoordinate, end, grid, usedGrid));
                }
            }

            for (var task : recursiveTasks) {
                task.fork();
            }

            for (var task : recursiveTasks) {
                var entry = task.join();
                if (entry.getValue()) {
                    entry.getKey().addFirst(coordinate);
                    return entry;
                }

            }
            return new AbstractMap.SimpleEntry<>(path, false);
        }

    }
}
