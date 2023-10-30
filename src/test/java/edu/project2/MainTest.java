package edu.project2;

import edu.project2.Generators.Generator;
import edu.project2.Generators.PrimsGenerator;
import edu.project2.Renderers.Renderer;
import edu.project2.Renderers.SimpleRenderer;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.Solver;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import org.junit.jupiter.api.Test;


import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        Generator primsGenerator = new PrimsGenerator();
        Maze maze = primsGenerator.generate(7, 11, 0);
        Renderer simpleRenderer = new SimpleRenderer();
//        LOGGER.info(simpleRenderer.render(maze));
        Coordinate start = new Coordinate(1,1);
        Coordinate end = new Coordinate(5,9);
        Solver solver = new BFSSolver();
        List<Coordinate> answer = solver.solve(maze, start, end);
        Main.LOGGER.info(simpleRenderer.render(maze, answer));
    }
}
