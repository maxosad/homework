package edu.project2;

import  edu.project2.generators.Generator;
import edu.project2.generators.GeneratorBridge;
import edu.project2.renderers.PrettyRenderer;
import edu.project2.renderers.Renderer;
import edu.project2.renderers.SimpleRenderer;
import edu.project2.solvers.BFSSolver;
import edu.project2.solvers.DFSSolver;
import edu.project2.solvers.Solver;
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
        Coordinate start = new Coordinate(0,0);
        Coordinate end = new Coordinate(5,9);
        Solver bfsSolver = new BFSSolver();
        Solver dfsSolver = new DFSSolver();

        var throwedException = assertThrows(RuntimeException.class, () -> bfsSolver.solve(maze, start, end));
        assertEquals("No path between start and end", throwedException.getMessage());
        throwedException = assertThrows(RuntimeException.class, () -> dfsSolver.solve(maze, start, end));
        assertEquals("No path between start and end", throwedException.getMessage());
    }

    @Test
    @DisplayName("renderer should throw exception height and width should be >= 3")
    void tryingGenerateTooSmallArray() {
        Generator bfsGenerator = new GeneratorBridge(GeneratorType.BFS);
        Generator primeGenerator = new GeneratorBridge(GeneratorType.PRIM);

        var throwedException = assertThrows(RuntimeException.class, () -> bfsGenerator.generate(1, 7, 0));
        assertEquals("height and width should be >= 3", throwedException.getMessage());
        throwedException = assertThrows(RuntimeException.class, () -> primeGenerator.generate(7, 1, 0));
        assertEquals("height and width should be >= 3", throwedException.getMessage());
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

        var throwedException = assertThrows(RuntimeException.class, () -> prettyRenderer.render(maze, path));
        assertEquals("Path goes through the wall", throwedException.getMessage());
        throwedException = assertThrows(RuntimeException.class, () -> simpleRenderer.render(maze, path));
        assertEquals("Path goes through the wall", throwedException.getMessage());
    }
}
