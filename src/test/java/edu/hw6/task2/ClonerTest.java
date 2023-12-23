package edu.hw6.task2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class ClonerTest {

    @Test
    void cloneFile() {
        Path path = Path.of("src/main/java/edu/hw6/task2/origFile/Tinkoff Bank Biggest Secret.txt");
        NamesSupplier namesSupplier = new NamesSupplier(path);
        Path path1 = namesSupplier.get();
        Path path2 = namesSupplier.get();


        try {
            Files.deleteIfExists(path1);
            Files.deleteIfExists(path2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cloner.cloneFile(path);
        Cloner.cloneFile(path);


        var result1 = Files.exists(path1);
        var result2 = Files.exists(path2);

        assertTrue(result1);
        assertTrue(result2);
    }
}
