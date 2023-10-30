package edu.project2;

import edu.project2.Generators.Generator;
import edu.project2.Generators.GeneratorBridge;
import edu.project2.Renderers.Renderer;
import edu.project2.Renderers.RendererBridge;
import edu.project2.Solvers.Solver;
import edu.project2.Solvers.SolverBridge;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.GeneratorType;
import edu.project2.model.Maze;
import edu.project2.model.RendererType;
import edu.project2.model.SolverType;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public final static Logger LOGGER = LogManager.getLogger();
    public static final String BFS = "BFS";
    public static final String SHOULD_BE_PASSAGE = "should be passage";
    public static final String SHOULD_BE_MORE_3 = "should be >= 3";
    private static Scanner scanner;
    private static Maze maze;

    private Main() { }

    private static Coordinate biConditionInputInts(String coordName, BiPredicate<Integer, Integer> biPredicate, String message) {
        int col;
        int row;
        while (true) {
            LOGGER.info("enter %s row and col, it should be passage".formatted(coordName));
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (biPredicate.test(row, col)) {
                return new Coordinate(row, col);
            }
            LOGGER.info("%s coordinate %s".formatted(coordName, message));
        }
    }

    private static <T> T chooseFromValues(Class<T> en, String name) {
        var ens = en.getEnumConstants();
        String choosenType;
        while (true) {
            LOGGER.info("Which %s do you want to choose?".formatted(name));
            for (var type : ens) {
                LOGGER.info(type);
            }
            choosenType = scanner.nextLine();
            for (var type : ens) {
                if (type.toString().equals(choosenType)) {
                    return type;
                }
            }
            LOGGER.info("you have to choose %s from presented values".formatted(name));
        }
    }

    private static int conditionInputInt(String name, Predicate<Integer> predicate, String message) {
        int input;
        while (true) {
            LOGGER.info("enter %s".formatted(name));
            input = scanner.nextInt();
            if (predicate.test(input)) {
                return input;
            }
            LOGGER.info("%s %s".formatted(name, message));
        }
    }


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Generator generator = new GeneratorBridge(chooseFromValues(GeneratorType.class, "generator"));

        int initHeight = conditionInputInt("height", x -> x >= 3, SHOULD_BE_MORE_3);
        int initWidth = conditionInputInt("width", x -> x >= 3, SHOULD_BE_MORE_3);

        int seed = conditionInputInt("seed", x -> true, "");

        scanner.nextLine();
        maze = generator.generate(initHeight, initWidth, seed);
        Renderer renderer = new RendererBridge(chooseFromValues(RendererType.class, "renderer"));
        LOGGER.info(renderer.render(maze));

        Solver solver = new SolverBridge(chooseFromValues(SolverType.class, "solver"));

        Coordinate start =
            biConditionInputInts("start",
                (row, col) -> maze.getGrid()[row][col].getType().equals(Cell.Type.PASSAGE),
                SHOULD_BE_PASSAGE
            );
        Coordinate end =
            biConditionInputInts("end",
                (row, col) -> maze.getGrid()[row][col].getType().equals(Cell.Type.PASSAGE),
                SHOULD_BE_PASSAGE);

        List<Coordinate> answer = solver.solve(maze, start, end);
        LOGGER.info(renderer.render(maze, answer));
    }
}
