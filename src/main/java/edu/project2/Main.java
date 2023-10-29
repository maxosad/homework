package edu.project2;

import edu.project2.Generators.Generator;
import edu.project2.Generators.PrimsGenerator;
import edu.project2.Renderers.Renderer;
import edu.project2.Renderers.SimpleRenderer;
import edu.project2.model.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Map;

public class Main {
    private Main() { }
    public final static Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
        Generator primsGenerator = new PrimsGenerator();
        Maze maze = primsGenerator.generate(4, 6, 0);
        Renderer simpleRenderer = new SimpleRenderer();
        LOGGER.info(simpleRenderer.render(maze));
    }
}
