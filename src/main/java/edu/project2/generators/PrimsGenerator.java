package edu.project2.generators;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Direction;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import static edu.project2.Config.DIRECTIONS_COUNT;

public class PrimsGenerator extends AbstractGenerator implements Generator {
    private void addPretendCoord(List<Coordinate> list, Coordinate c) {
        for (int i = 0; i < DIRECTIONS_COUNT; i++) {
            int newCol = c.col() + SDVIG[i][1];
            int newRow = c.row() + SDVIG[i][0];
            if (0 <= newCol && newCol < width
                && 0 <= newRow && newRow < height
                && grid[newRow][newCol].getType().equals(Cell.Type.WALL)
            ) {
                var newCoordinate = new Coordinate(newRow, newCol);
                if (!usedPretend.getOrDefault(newCoordinate, false)) {
                    list.add(newCoordinate);
                    usedPretend.put(newCoordinate, true);
                }
            }
        }
    }

    @Override
    public Maze generate(int height, int width, int seed) {
        usedPretend = new HashMap<>();
        prepare(height, width, seed);
        grid[startCoordinate.row()][startCoordinate.col()].setType(Cell.Type.PASSAGE);
        List<Coordinate> pretendent = new ArrayList<>();

        addPretendCoord(pretendent, startCoordinate);

        while (!pretendent.isEmpty()) {
            int chosenIndex = random.nextInt(pretendent.size());
            Coordinate c = pretendent.remove(chosenIndex);
            int row = c.row();
            int col = c.col();
            grid[row][col].setType(Cell.Type.PASSAGE);

            List<Direction> dirs = Direction.DIRECTION_SETUP;
            Collections.shuffle(dirs, random);

            boolean isPassageEstablished = false;
            for (var direction : dirs) {
                if (isPassageEstablished) {
                    break;
                }
                switch (direction) {
                    case TOP -> {
                        int newRow = row - 2;
                        if (newRow >= 0 && grid[newRow][col].getType().equals(Cell.Type.PASSAGE)) {
                            grid[row - 1][col].setType(Cell.Type.PASSAGE);
                            isPassageEstablished = true;
                        }
                    }
                    case RIGHT -> {
                        int newCol = col + 2;
                        if (newCol < width && grid[row][newCol].getType().equals(Cell.Type.PASSAGE)) {
                            grid[row][col + 1].setType(Cell.Type.PASSAGE);
                            isPassageEstablished = true;
                        }
                    }
                    case DOWN -> {
                        int newRow = row + 2;
                        if (newRow < height && grid[newRow][col].getType().equals(Cell.Type.PASSAGE)) {
                            grid[row + 1][col].setType(Cell.Type.PASSAGE);
                            isPassageEstablished = true;
                        }
                    }
                    case LEFT -> {
                        int newCol = col - 2;
                        if (newCol >= 0 && grid[row][newCol].getType().equals(Cell.Type.PASSAGE)) {
                            grid[row][col - 1].setType(Cell.Type.PASSAGE);
                            isPassageEstablished = true;
                        }
                    }
                    default -> { }
                }
            }
            addPretendCoord(pretendent, c);
        }
        return new Maze(height, width, grid);
    }
}
