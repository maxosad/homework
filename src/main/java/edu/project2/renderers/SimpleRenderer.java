package edu.project2.renderers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.model.RenderCell;
import java.util.Arrays;
import java.util.List;

public class SimpleRenderer extends AbstractRendered implements Renderer {
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
        RenderCell[][] renderGrid = makeEmptyRenderGrid(maze, path);

        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (var el : renderGrid) {
            sb.append(Arrays.toString(el)).append("\n");
        }
        return sb.toString();
    }
}
