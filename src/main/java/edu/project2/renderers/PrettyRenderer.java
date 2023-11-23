package edu.project2.renderers;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.model.RenderCell;
import java.util.List;

public class PrettyRenderer extends AbstractRendered implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();
        Cell[][] mazeGrid = maze.getGrid();
        sb.append("\n");
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                sb.append(mazeGrid[row][col].getType().equals(Cell.Type.WALL) ? "▉" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        RenderCell[][] renderGrid = makeEmptyRenderGrid(maze, path);
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                String toAppend = switch (renderGrid[row][col].getType()) {
                    case WALL -> "▉";
                    case PASSAGE -> " ";
                    case PATH -> ".";
                };
                sb.append(toAppend);
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
