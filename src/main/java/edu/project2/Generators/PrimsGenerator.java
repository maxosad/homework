package edu.project2.Generators;

import edu.project2.model.Maze;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PrimsGenerator implements Generator {
    private HashMap<Coordinate, Boolean> usedPretend;
    private int width;
    private int height;
    private Cell[][] grid;
    private void addPreCord(List<Coordinate> list, int row, int col) {
        int[][] sdvig = new int[][] {{0, -2}, {0, 2}, {2, 0}, {-2, 0}};
        for (int i = 0; i < 4; i++) {
            int newCol = col + sdvig[i][1];
            int newRow = row + sdvig[i][0];
            if (0 <= newCol && newCol < width
                && 0 <= newRow && newRow < height
                && grid[newRow][newCol].getType().equals(Cell.Type.WALL)
            ) {
                var c = new Coordinate(newRow, newCol);
                if (!usedPretend.getOrDefault(c, false)) {
                    list.add(c);
                    usedPretend.put(c, true);
                }
            }
        }
    }

    @Override
    public Maze generate(int height, int width, int seed) {
        this.height = height;
        this.width = width;
        Random random = new Random(seed);
        grid = new Cell[height][width];
        usedPretend = new HashMap<>();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }

        int startCoordinateRow = random.nextInt(height / 2) * 2 + 1;
        int startCoordinateCol = random.nextInt(width / 2) * 2 + 1;

        System.out.println(startCoordinateRow + " " + startCoordinateCol);

        grid[startCoordinateRow][startCoordinateCol].setType(Cell.Type.PASSAGE);

        List<Coordinate> pretendCoordinate = new ArrayList<>();

        usedPretend.put(new Coordinate(startCoordinateRow, startCoordinateCol), true);
        addPreCord(pretendCoordinate, startCoordinateRow, startCoordinateCol);

        while (!pretendCoordinate.isEmpty()) {
//            System.out.println(pretendCoordinate);
//            System.out.println();
            int chosenIndex = random.nextInt(pretendCoordinate.size());
            Coordinate c = pretendCoordinate.remove(chosenIndex);
            int row = c.row();
            int col = c.col();
            grid[row][col].setType(Cell.Type.PASSAGE);

            List<Direction> dirs = Direction.getDirectionSetup;
            Collections.shuffle(dirs, random);

            boolean f = false;
            for (var direction : dirs) {
                if (f) {
                    break;
                }
                switch (direction) {
                    case TOP -> {
                        int newRow = row - 2;
                        if (newRow >= 0 && grid[newRow][col].getType().equals(Cell.Type.PASSAGE) ) {
                            grid[row - 1][col].setType(Cell.Type.PASSAGE);
                            f = true;
                        }
                    }
                    case RIGHT -> {
                        int newCol = col + 2;
                        if (newCol < width && grid[row][newCol].getType().equals(Cell.Type.PASSAGE) ) {
                            grid[row][col + 1].setType(Cell.Type.PASSAGE);
                            f = true;
                        }
                    }
                    case DOWN -> {
                        int newRow = row + 2;
                        if (newRow < height && grid[newRow][col].getType().equals(Cell.Type.PASSAGE) ) {
                            grid[row + 1][col].setType(Cell.Type.PASSAGE);
                            f = true;
                        }
                    }
                    case LEFT -> {
                        int newCol = col - 2;
                        if (newCol >= 0 && grid[row][newCol].getType().equals(Cell.Type.PASSAGE) ) {
                            grid[row][col-1].setType(Cell.Type.PASSAGE);
                            f = true;
                        }
                    }
                }
            }

            addPreCord(pretendCoordinate, row, col);

        }
        return new Maze(height, width, grid);
    }
    private static void check(List<Integer> l) {
        l.add(1);
    }
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        check(l);
        System.out.println(l);
    }
}
