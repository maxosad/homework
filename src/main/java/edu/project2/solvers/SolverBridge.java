package edu.project2.solvers;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.model.SolverType;
import java.util.List;

public class SolverBridge implements Solver {
    private final Solver solver;

    public SolverBridge(SolverType solverType) {
        solver = solverType.equals(SolverType.BFS) ? new BFSSolver() : new DFSSolver();
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        return solver.solve(maze, start, end);
    }
}
