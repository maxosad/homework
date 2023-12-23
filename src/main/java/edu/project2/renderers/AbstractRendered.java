package edu.project2.renderers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.model.RenderCell;
import java.util.List;

public abstract class AbstractRendered implements Renderer {

    @Override
    public abstract String render(Maze maze);

    @Override
    public abstract String render(Maze maze, List<Coordinate> path);

    public static RenderCell[][] makeEmptyRenderGrid(Maze maze, List<Coordinate> path) {
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
        return grid;
    }
}
