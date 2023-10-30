package edu.project2.Generators;

import edu.project2.model.Maze;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PrimsGenerator extends AbstractGenerator implements Generator {
    private void addPretendCoord(List<Coordinate> list, int row, int col) {
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
        usedPretend = new HashMap<>();
        prepare(height, width, seed);

        int startCoordinateRow = random.nextInt(height / 2) * 2 + 1;
        int startCoordinateCol = random.nextInt(width / 2) * 2 + 1;

        grid[startCoordinateRow][startCoordinateCol].setType(Cell.Type.PASSAGE);

        List<Coordinate> pretendent = new ArrayList<>();
        usedPretend.put(new Coordinate(startCoordinateRow, startCoordinateCol), true);
        addPretendCoord(pretendent, startCoordinateRow, startCoordinateCol);

        while (!pretendent.isEmpty()) {
            int chosenIndex = random.nextInt(pretendent.size());
            Coordinate c = pretendent.remove(chosenIndex);
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

            addPretendCoord(pretendent, row, col);

        }
        return new Maze(height, width, grid);
    }
}
