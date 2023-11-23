package edu.project2;

import edu.project2.generators.Generator;
import edu.project2.generators.GeneratorBridge;
import edu.project2.model.Coordinate;
import edu.project2.model.GeneratorType;
import edu.project2.model.Maze;
import edu.project2.model.RendererType;
import edu.project2.renderers.PrettyRenderer;
import edu.project2.renderers.Renderer;
import edu.project2.renderers.RendererBridge;
import edu.project2.solvers.BFSSolver;
import edu.project2.solvers.Solver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenderersTest {
    public static List<Coordinate> path;
    public static Maze maze;

    @BeforeAll static void setUp() {
        Generator bfsGenerator = new GeneratorBridge(GeneratorType.BFS);
        maze = bfsGenerator.generate(7, 12, 0);
        Coordinate start = new Coordinate(1,1);
        Coordinate end = new Coordinate(5,9);
        Solver solver = new BFSSolver();
        path = solver.solve(maze, start, end);
    }

    @Test
    void prettyRendererTest() {
        Renderer renderer = new RendererBridge(RendererType.PRETTY);

        String clearMaze = renderer.render(maze);
        String mazeWithPath = renderer.render(maze, path);

        assertEquals("\n" +
            "▉▉▉▉▉▉▉▉▉▉▉▉\n" +
            "▉          ▉\n" +
            "▉▉▉▉▉▉▉▉▉ ▉▉\n" +
            "▉         ▉▉\n" +
            "▉▉▉▉▉▉▉ ▉ ▉▉\n" +
            "▉       ▉ ▉▉\n" +
            "▉▉▉▉▉▉▉▉▉▉▉▉\n", clearMaze);
        assertEquals("\n" +
            "▉▉▉▉▉▉▉▉▉▉▉▉\n" +
            "▉......... ▉\n" +
            "▉▉▉▉▉▉▉▉▉.▉▉\n" +
            "▉        .▉▉\n" +
            "▉▉▉▉▉▉▉ ▉.▉▉\n" +
            "▉       ▉.▉▉\n" +
            "▉▉▉▉▉▉▉▉▉▉▉▉\n", mazeWithPath);
    }
}
