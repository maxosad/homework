package edu.project2.Generators;

import edu.project2.model.Maze;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrimsGenerator implements Generator {

    private int width;
    private int height;
    private Cell[][] grid;
    private void addPreCord(List<Coordinate> list, int row, int col) {
        if (col - 2 >= 0 && grid[row][col - 2].getType().equals(Cell.Type.WALL)) {
            list.add(new Coordinate(row, col - 2));
        }
        if (col + 2 < width && grid[row][col + 2].getType().equals(Cell.Type.WALL)) {
            list.add(new Coordinate(row, col + 2));
        }
        if (row - 2 >= 0 && grid[row - 2][col].getType().equals(Cell.Type.WALL)) {
            list.add(new Coordinate(row - 2, col));
        }
        if (row + 2 < height && grid[row + 2][col].getType().equals(Cell.Type.WALL)) {
            list.add(new Coordinate(row + 2, col));
        }
    }

    @Override
    public Maze generate(int height, int width, int seed) {
        this.height = height;
        this.width = width;
        Random random = new Random(seed);
       grid = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }

        int startCoordinateRow = random.nextInt(height / 2) * 2 + 1;
        int startCoordinateCol = random.nextInt(width / 2) * 2 + 1;

        grid[startCoordinateRow][startCoordinateCol].setType(Cell.Type.PASSAGE);

        List<Coordinate> pretendCoordinate = new ArrayList<>();
        addPreCord(pretendCoordinate, startCoordinateRow, startCoordinateCol);

        while (!pretendCoordinate.isEmpty()) {
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
                        int newCol = col - 2;
                        if (newCol >= 0 && grid[row][newCol].getType().equals(Cell.Type.PASSAGE) ) {
                            grid[row][col-1].setType(Cell.Type.PASSAGE);
                        }
                        f = true;
                    }
                    case RIGHT -> {
                        int newRow = row + 2;
                        if (newRow < height && grid[newRow][col].getType().equals(Cell.Type.PASSAGE) ) {
                            grid[row + 1][col].setType(Cell.Type.PASSAGE);
                        }
                        f = true;
                    }
                    case DOWN -> {
                        int newCol = col + 2;
                        if (newCol < width && grid[row][newCol].getType().equals(Cell.Type.PASSAGE) ) {
                            grid[row][col + 1].setType(Cell.Type.PASSAGE);
                        }
                        f = true;
                    }
                    case LEFT -> {
                        int newRow = row - 2;
                        if (newRow >= 0 && grid[newRow][col].getType().equals(Cell.Type.PASSAGE) ) {
                            grid[row - 1][col].setType(Cell.Type.PASSAGE);
                        }
                        f = true;
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
