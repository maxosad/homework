package edu.project2;

import edu.project2.generators.Generator;
import edu.project2.generators.GeneratorBridge;
import edu.project2.model.Coordinate;
import edu.project2.model.GeneratorType;
import edu.project2.model.Maze;
import edu.project2.model.SolverType;
import edu.project2.solvers.BFSSolver;
import edu.project2.solvers.Solver;
import edu.project2.solvers.SolverBridge;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolversTest {

    public static Maze maze;
    public static Coordinate start;
    public static Coordinate end;
    public static List<Coordinate> rightPath;

    @BeforeAll
    static void setUp() {
        Generator bfsGenerator = new GeneratorBridge(GeneratorType.BFS);
        maze = bfsGenerator.generate(7, 12, 0);
        start = new Coordinate(1, 1);
        end = new Coordinate(5, 9);

        rightPath = List.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3),
            new Coordinate(1, 4),
            new Coordinate(1, 5),
            new Coordinate(1, 6),
            new Coordinate(1, 7),
            new Coordinate(1, 8),
            new Coordinate(1, 9),
            new Coordinate(2, 9),
            new Coordinate(3, 9),
            new Coordinate(4, 9),
            new Coordinate(5, 9));
    }

    @Test
    void bfsSolverTest() {
        Solver bfsSolver = new SolverBridge(SolverType.BFS);

        List<Coordinate> path = bfsSolver.solve(maze, start, end);

        assertEquals(rightPath, path);

    }

    @Test
    void dfsSolverTest() {
        Solver dfsSolver = new SolverBridge(SolverType.DFS);

        List<Coordinate> path = dfsSolver.solve(maze, start, end);

        assertEquals(rightPath, path);

    }
}
