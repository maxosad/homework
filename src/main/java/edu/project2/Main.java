package edu.project2;

import edu.project2.generators.Generator;
import edu.project2.generators.GeneratorBridge;
import edu.project2.renderers.Renderer;
import edu.project2.renderers.RendererBridge;
import edu.project2.solvers.Solver;
import edu.project2.solvers.SolverBridge;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.GeneratorType;
import edu.project2.model.Maze;
import edu.project2.model.RendererType;
import edu.project2.model.SolverType;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project2.Config.HEIGHT_WIDTH_LIMIT;

public class Main {

    public  static final Logger LOGGER = LogManager.getLogger();
    public static final String SHOULD_BE_PASSAGE = "should be passage";
    public static final String SHOULD_BE_MORE_3 = "should be >= 3";
    private static Scanner scanner;

    private Main() { }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Generator generator = new GeneratorBridge(chooseFromValues(GeneratorType.class, "generator"));

        int initHeight = conditionInputInt("height", x -> x >= HEIGHT_WIDTH_LIMIT, SHOULD_BE_MORE_3);
        int initWidth = conditionInputInt("width", x -> x >= HEIGHT_WIDTH_LIMIT, SHOULD_BE_MORE_3);
        int seed = conditionInputInt("seed", x -> true, "");

        scanner.nextLine();
        Maze maze = generator.generate(initHeight, initWidth, seed);
        Renderer renderer = new RendererBridge(chooseFromValues(RendererType.class, "renderer"));
        LOGGER.info(renderer.render(maze));

        Solver solver = new SolverBridge(chooseFromValues(SolverType.class, "solver"));

        BiPredicate<Integer, Integer> colRowValidPredicate = (row, col) ->
            row < maze.getHeight() && col < maze.getWidth();

        Coordinate start =
            biConditionInputInts("start",
                (row, col) -> maze.getGrid()[row][col].getType().equals(Cell.Type.PASSAGE),
                colRowValidPredicate
            );
        Coordinate end =
            biConditionInputInts("end",
                (row, col) -> maze.getGrid()[row][col].getType().equals(Cell.Type.PASSAGE),
                colRowValidPredicate);

        List<Coordinate> answer = solver.solve(maze, start, end);
        LOGGER.info(renderer.render(maze, answer));
    }



    private static Coordinate biConditionInputInts(String coordName, BiPredicate<Integer, Integer> passageBiPredicate,
        BiPredicate<Integer, Integer> colRowValidPredicate) {

        int col;
        int row;
        while (true) {
            LOGGER.info("enter {} row and col indexes, the cell should be passage", coordName);
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (!colRowValidPredicate.test(row, col)){
                LOGGER.info("row and col indexes should be less then mazeHeight and mazeGrid");
                continue;
            }
            if (passageBiPredicate.test(row, col)) {
                return new Coordinate(row, col);
            }
            LOGGER.info("{} coordinate {}", coordName, SHOULD_BE_PASSAGE);
        }
    }

    private static <T> T chooseFromValues(Class<T> en, String name) {
        T[] ens = en.getEnumConstants();
        String choosenType;
        while (true) {
            LOGGER.info("Which {} do you want to choose?", name);
            for (var type : ens) {
                LOGGER.info(type);
            }
            choosenType = scanner.nextLine();
            for (var type : ens) {
                if (type.toString().equals(choosenType)) {
                    return type;
                }
            }
            LOGGER.info("you have to choose {} from presented values", name);
        }
    }

    private static int conditionInputInt(String name, IntPredicate predicate, String message) {
        int input;
        while (true) {
            LOGGER.info("enter {}", name);
            input = scanner.nextInt();
            if (predicate.test(input)) {
                return input;
            }
            LOGGER.info("{} {}", name, message);
        }
    }
}
