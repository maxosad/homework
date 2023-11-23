package edu.project2;

import edu.project2.generators.Generator;
import edu.project2.generators.GeneratorBridge;
import edu.project2.generators.PrimsGenerator;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.GeneratorType;
import edu.project2.model.Maze;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratorTest {
    private static int height = 7;
    private static int width = 12;
    private static int seed = 0;

    @Test
    void bfsGeneratorTest() {
        Generator generator = new GeneratorBridge(GeneratorType.BFS);

        Maze result = generator.generate(height, width, seed);
        Maze result1 = generator.generate(height, width, seed);

        assertEquals(result1.getHeight(), result.getHeight());
        assertEquals(result1.getWidth(), result.getWidth());
        assertTrue(Arrays.deepEquals(result1.getGrid(), result.getGrid()));
    }

    @Test
    void primGeneratorTest() {
        Generator generator = new GeneratorBridge(GeneratorType.PRIM);

        Maze result = generator.generate(height, width, seed);
        Maze result1 = generator.generate(height, width, seed);

        assertEquals(result1.getHeight(), result.getHeight());
        assertEquals(result1.getWidth(), result.getWidth());
        assertTrue(Arrays.deepEquals(result1.getGrid(), result.getGrid()));
    }
}
