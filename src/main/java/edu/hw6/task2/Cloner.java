package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Cloner {

    public static final Logger LOGGER = LogManager.getLogger();

    private Cloner() { }

    public static void cloneFile(Path path) {
        NamesSupplier namesSupplier = new NamesSupplier(path);
        Path newPath = Stream.generate(namesSupplier)
            .filter(Files::notExists)
            .findFirst().orElseThrow(() -> new NullPointerException("All allowed file names are already used"));

        try {
            Files.copy(path, newPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info(path);
    }
}
