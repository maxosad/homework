package edu.project2.generators;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static edu.project2.Config.DIRECTIONS_LIST;

public class BFSGenerator extends AbstractGenerator implements Generator {
    @Override
    public Maze generate(int height, int width, int seed) {
        prepare(height, width, seed);

        ArrayDeque<Coordinate> pretendent = new ArrayDeque<>();
        pretendent.add(startCoordinate);

        while (!pretendent.isEmpty()) {
            Coordinate c = pretendent.pollFirst();

            int row = c.row();
            int col = c.col();
            grid[row][col].setType(Cell.Type.PASSAGE);

            List<Integer> sdvig = new ArrayList<>(DIRECTIONS_LIST);
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
