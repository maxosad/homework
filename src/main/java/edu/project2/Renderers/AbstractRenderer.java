package edu.project2.Renderers;

import edu.project2.model.Coordinate;
import edu.project2.model.RenderCell;
import java.util.List;

public abstract class AbstractRenderer implements Renderer {
    protected static void markPath(List<Coordinate> path, RenderCell[][] grid) {
        for (var coord : path) {
            if (!grid[coord.row()][coord.col()].getType().equals(RenderCell.Type.PASSAGE)) {
                throw new RuntimeException("Path goes through the wall");
            }
            grid[coord.row()][coord.col()].setType(RenderCell.Type.PATH);
        }
    }
}
