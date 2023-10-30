package edu.project2.Generators;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BFSGenerator extends AbstractGenerator implements Generator {
    @Override
    public Maze generate(int height, int width, int seed) {
        prepare(height, width, seed);

        int startCoordinateRow = random.nextInt(height / 2) * 2 + 1;
        int startCoordinateCol = random.nextInt(width / 2) * 2 + 1;

        ArrayDeque<Coordinate> pretendent = new ArrayDeque<>();
        Coordinate startCoordinate = new Coordinate(startCoordinateRow, startCoordinateCol);
        pretendent.add(startCoordinate);

        while (!pretendent.isEmpty()) {
            Coordinate c = pretendent.pollFirst();
            usedPretend.put(c, true);
            int row = c.row();
            int col = c.col();
            grid[row][col].setType(Cell.Type.PASSAGE);

            List<Integer> sdvig = new ArrayList<>(List.of(0, 1, 2, 3));
            Collections.shuffle(sdvig, random);

            for (var sdvigIndex : sdvig) {
                int newCol = c.col() + SDVIG[sdvigIndex][1];
                int newRow = c.row() + SDVIG[sdvigIndex][0];
                if (0 <= newCol && newCol < width
                    && 0 <= newRow && newRow < height
                    && grid[newRow][newCol].getType().equals(Cell.Type.WALL)
                ) {
                    var newCoordinate = new Coordinate(newRow, newCol);
                    if (!usedPretend.getOrDefault(newCoordinate, false)) {
                        usedPretend.put(newCoordinate, true);
                        var between = between(c, newCoordinate);
                        grid[between.row()][between.col()].setType(Cell.Type.PASSAGE);
                        pretendent.add(newCoordinate);
                    }
                }
            }
        }

        return new Maze(height, width, grid);
    }
}
