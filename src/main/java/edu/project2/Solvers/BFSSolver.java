package edu.project2.Solvers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class BFSSolver implements Solver {
    private static final int[][] SDVIG = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    private boolean[][] usedGrid;
    private int height;
    private int width;
    private Queue<Coordinate> queue;
    private HashMap<Coordinate, Coordinate> prevs;
    private Deque<Coordinate> answer;
    private Cell[][] grid;
    private void restorePath(Coordinate c) {
        answer = new ArrayDeque<>();
        do {
            answer.addFirst(c);
            c = prevs.get(c);
        } while (c != null);
    }

    private void addCoordinates(Coordinate c) {
        for (int sdvid = 0; sdvid < 4; sdvid++) {
            Coordinate newC = new Coordinate(c.row() + SDVIG[sdvid][0], c.col() + SDVIG[sdvid][1]);
            if (0 <= newC.col() && newC.col() < width
                && 0 <= newC.row() && newC.row() < height
                && !usedGrid[newC.row()][newC.col()]
                && grid[newC.row()][newC.col()].getType().equals(Cell.Type.PASSAGE)) {
                prevs.put(newC, c);
                queue.add(newC);
            }
        }
    }
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
            Coordinate c = queue.poll();
            usedGrid[c.row()][c.col()] = true;
            if (c.equals(end)) {
                restorePath(c);
                return answer.stream().toList();
            }
            addCoordinates(c);
        }

        throw new RuntimeException("No Path");
    }
}
