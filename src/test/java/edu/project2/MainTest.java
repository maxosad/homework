package edu.project2;

import edu.project2.Generators.AbstractGenerator;
import edu.project2.Generators.Generator;
import edu.project2.Generators.GeneratorBridge;
import edu.project2.Generators.PrimsGenerator;
import edu.project2.Renderers.PrettyRenderer;
import edu.project2.Renderers.Renderer;
import edu.project2.Renderers.SimpleRenderer;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.DFSSolver;
import edu.project2.Solvers.Solver;
import edu.project2.model.Coordinate;
import edu.project2.model.GeneratorType;
import edu.project2.model.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("should find precounted path")
    void findPath() {
        Generator bfsGenerator = new GeneratorBridge(GeneratorType.BFS);
        Maze maze = bfsGenerator.generate(7, 12, 0);
        Renderer prettyRenderer = new PrettyRenderer();
        Coordinate start = new Coordinate(1,1);
        Coordinate end = new Coordinate(5,9);
        Solver solver = new BFSSolver();
        List<Coordinate> answer = solver.solve(maze, start, end);
        assertEquals("\n" +
            "▉▉▉▉▉▉▉▉▉▉▉▉\n" +
            "▉......... ▉\n" +
            "▉▉▉▉▉▉▉▉▉.▉▉\n" +
            "▉        .▉▉\n" +
            "▉▉▉▉▉▉▉ ▉.▉▉\n" +
            "▉       ▉.▉▉\n" +
            "▉▉▉▉▉▉▉▉▉▉▉▉\n", prettyRenderer.render(maze, answer));
    }

    @Test
    @DisplayName("solvers should throw exception No path")
    void throwExc() {
        Generator bfsGenerator = new GeneratorBridge(GeneratorType.BFS);
        Maze maze = bfsGenerator.generate(7, 12, 0);
        Renderer prettyRenderer = new PrettyRenderer();
        Coordinate start = new Coordinate(0,0);
        Coordinate end = new Coordinate(5,9);
        Solver bfsSolver = new BFSSolver();
        Solver dfsSolver = new DFSSolver();

        var thr = assertThrows(RuntimeException.class, () -> bfsSolver.solve(maze, start, end));
        assertEquals("No path", thr.getMessage());
        thr = assertThrows(RuntimeException.class, () -> dfsSolver.solve(maze, start, end));
        assertEquals("No path", thr.getMessage());
    }

    @Test
    @DisplayName("renderer should throw exception height and width should be >= 3")
    void tryingGenerateTooSmallArray() {
        Generator bfsGenerator = new GeneratorBridge(GeneratorType.BFS);
        Generator primeGenerator = new GeneratorBridge(GeneratorType.PRIM);

        var thr = assertThrows(RuntimeException.class, () -> bfsGenerator.generate(1, 7, 0));
        assertEquals("height and width should be >= 3", thr.getMessage());
        thr = assertThrows(RuntimeException.class, () -> primeGenerator.generate(7, 1, 0));
        assertEquals("height and width should be >= 3", thr.getMessage());
    }

    @Test
    @DisplayName("generator should throw exception Path goes through the wall")
    void pathThroughtWall() {
        Generator bfsGenerator = new GeneratorBridge(GeneratorType.BFS);
        Maze maze = bfsGenerator.generate(7, 12, 0);
        Renderer prettyRenderer = new PrettyRenderer();
        Renderer simpleRenderer = new SimpleRenderer();
        List<Coordinate> path = List.of(new Coordinate(0, 0),
            new Coordinate(1, 1));

        var thr = assertThrows(RuntimeException.class, () -> prettyRenderer.render(maze, path));
        assertEquals("Path goes through the wall", thr.getMessage());
        thr = assertThrows(RuntimeException.class, () -> simpleRenderer.render(maze, path));
        assertEquals("Path goes through the wall", thr.getMessage());
    }
}
