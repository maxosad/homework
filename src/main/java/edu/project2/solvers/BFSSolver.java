package edu.project2.solvers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import static edu.project2.Config.DIRECTIONS_COUNT;

public class BFSSolver implements Solver {
    private static final int[][] SDVIG = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    private boolean[][] usedGrid;
    private int height;
    private int width;
    private Queue<Coordinate> queue;
    private Map<Coordinate, Coordinate> prevs;
    private Deque<Coordinate> answer;
    private Cell[][] grid;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        height = maze.getHeight();
        width = maze.getWidth();
        usedGrid = new boolean[height][width];
        grid = maze.getGrid();
        prevs = new HashMap<>();
        queue = new ArrayDeque<>();
        queue.add(start);
        prevs.put(start, null);

        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            usedGrid[coordinate.row()][coordinate.col()] = true;
            if (coordinate.equals(end)) {
                restorePath(coordinate);
                return answer.stream().toList();
            }
            addCoordinates(coordinate);
        }

        throw new RuntimeException("No path between start and end");
    }

    private void restorePath(Coordinate coordinate) {
        answer = new ArrayDeque<>();
        Coordinate newCoordinate = new Coordinate(coordinate.row(), coordinate.col());
        do {
            answer.addFirst(newCoordinate);
            newCoordinate = prevs.get(newCoordinate);
        } while (newCoordinate != null);
    }

    private void addCoordinates(Coordinate coordinate) {
        for (int sdvidIndex = 0; sdvidIndex < DIRECTIONS_COUNT; sdvidIndex++) {
            Coordinate newCoordinate = new Coordinate(coordinate.row() + SDVIG[sdvidIndex][0],
                coordinate.col() + SDVIG[sdvidIndex][1]);
            if (0 <= newCoordinate.col() && newCoordinate.col() < width
                && 0 <= newCoordinate.row() && newCoordinate.row() < height
                && !usedGrid[newCoordinate.row()][newCoordinate.col()]
                && grid[newCoordinate.row()][newCoordinate.col()].getType().equals(Cell.Type.PASSAGE)) {
                prevs.put(newCoordinate, coordinate);
                queue.add(newCoordinate);
            }
        }
    }
}
