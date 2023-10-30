package edu.project2;

import edu.project2.Generators.BFSGenerator;
import edu.project2.Generators.Generator;
import edu.project2.Generators.PrimsGenerator;
import edu.project2.Renderers.Renderer;
import edu.project2.Renderers.SimpleRenderer;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.Solver;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Main {

    public final static Logger LOGGER = LogManager.getLogger();
    private Main() { }
    public static void main(String[] args) {
        Generator primsGenerator = new PrimsGenerator();
        Generator bfsGenerator = new BFSGenerator();
        Maze maze = bfsGenerator.generate(7, 11, 0);
        Renderer simpleRenderer = new SimpleRenderer();
        LOGGER.info(simpleRenderer.render(maze));
        Coordinate start = new Coordinate(1,1);
        Coordinate end = new Coordinate(5,9);
        Solver solver = new BFSSolver();
        List<Coordinate> answer = solver.solve(maze, start, end);
        LOGGER.info(simpleRenderer.render(maze, answer));
    }
}
