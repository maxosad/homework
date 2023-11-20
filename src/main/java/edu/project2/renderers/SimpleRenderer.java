package edu.project2.renderers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.model.RenderCell;
import java.util.Arrays;
import java.util.List;

public class SimpleRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        Cell[][] grid = maze.getGrid();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (var el : grid) {
            sb.append(Arrays.toString(el)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        Cell[][] mazeGrid = maze.getGrid();
        RenderCell[][] grid = new RenderCell[maze.getHeight()][maze.getWidth()];
        for (var line : mazeGrid) {
            for (var cell : line) {
                grid[cell.getRow()][cell.getCol()] = new RenderCell(cell);
            }
        }

        for (var coord : path) {
            if (!grid[coord.row()][coord.col()].getType().equals(RenderCell.Type.PASSAGE)) {
                throw new RuntimeException("Path goes through the wall");
            }
            grid[coord.row()][coord.col()].setType(RenderCell.Type.PATH);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (var el : grid) {
            sb.append(Arrays.toString(el)).append("\n");
        }
        return sb.toString();
    }
}
