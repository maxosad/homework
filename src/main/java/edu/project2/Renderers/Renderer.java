package edu.project2.Renderers;

import edu.project2.model.Maze;
import edu.project2.model.Coordinate;

import java.util.List;

public interface Renderer {
    String render(Maze maze);
    String render(Maze maze, List<Coordinate> path);
}
