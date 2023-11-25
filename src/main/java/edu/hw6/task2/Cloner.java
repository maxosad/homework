package edu.hw6.task2;

import edu.hw6.Main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


public class Cloner {

    private Cloner() { }

    public static void cloneFile(Path path) {
        NamesSupplier namesSupplier = new NamesSupplier(path);
        Path newPath = Stream.generate(namesSupplier)
            .filter(Files::notExists)
            .findFirst().orElse(null);

        try {
            Files.copy(path, newPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.LOGGER.info(path);
    }
}
