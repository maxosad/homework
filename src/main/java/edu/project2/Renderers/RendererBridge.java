package edu.project2.Renderers;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.model.RendererType;
import java.util.List;

public class RendererBridge implements Renderer {

    private final Renderer renderer;

    public RendererBridge(RendererType rendererType) {
        renderer = rendererType.equals(RendererType.SIMPLE) ? new SimpleRenderer() : new PrettyRenderer();
    }

    @Override
    public String render(Maze maze) {
        return renderer.render(maze);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return renderer.render(maze, path);
    }
}
