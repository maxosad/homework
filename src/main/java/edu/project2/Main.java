package edu.project2;

import edu.project2.Generators.Generator;
import edu.project2.Generators.GeneratorBridge;
import edu.project2.Renderers.PrettyRenderer;
import edu.project2.Renderers.Renderer;
import edu.project2.Renderers.SimpleRenderer;
import edu.project2.Solvers.BFSSolver;
import edu.project2.Solvers.DFSSolver;
import edu.project2.Solvers.Solver;
import edu.project2.model.Coordinate;
import edu.project2.model.GeneratorType;
import edu.project2.model.Maze;
import edu.project2.model.RendererType;
import edu.project2.model.SolverType;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public final static Logger LOGGER = LogManager.getLogger();
    public static final String BFS = "BFS";

    private Main() { }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Which generator do you want to choose?");
        for (var type : GeneratorType.values()) {
            LOGGER.info(type.toString());
        }
        String generatorType = scanner.nextLine();
        Generator generator =
            new GeneratorBridge(generatorType.equals(BFS) ? GeneratorType.BFS : GeneratorType.PRIM);
        LOGGER.info("enter initial height >= 3");
        int initHeight = scanner.nextInt();
        LOGGER.info("enter initial width >= 3");
        int initWidth = scanner.nextInt();
        LOGGER.info("enter seed");
        int seed = scanner.nextInt();
        LOGGER.info("Which renderer do you want to choose?");
        for (var type : RendererType.values()) {
            LOGGER.info(type.toString());
        }
        String rendererType = scanner.nextLine();
        Maze maze = generator.generate(initHeight, initWidth, seed);
        Renderer renderer = rendererType.equals("SIMPLE") ? new SimpleRenderer() : new PrettyRenderer();
        LOGGER.info(renderer.render(maze));
        LOGGER.info("Which solver do you want to choose?");
        for (var type : SolverType.values()) {
            LOGGER.info(type.toString());
        }
        String solverType = scanner.nextLine();



        Solver solver = solverType.equals(BFS) ? new BFSSolver() : new DFSSolver();


        LOGGER.info("enter start row");
        int startRow = scanner.nextInt();
        LOGGER.info("enter start col");
        int startCol = scanner.nextInt();
        Coordinate start = new Coordinate(startRow, startCol);
        LOGGER.info("enter end row");
        int endRow = scanner.nextInt();
        LOGGER.info("enter end col");
        int endCol = scanner.nextInt();
        Coordinate end = new Coordinate(endRow, endCol);

        List<Coordinate> answer = solver.solve(maze, start, end);
        LOGGER.info(renderer.render(maze, answer));
    }
}
