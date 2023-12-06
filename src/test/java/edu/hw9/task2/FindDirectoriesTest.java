package edu.hw9.task2;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.*;

class FindDirectoriesTest {

    @Test
    void compute() {
        Path path = Path.of("src/main/java/edu/hw9/task2/files");
        List<Path> expected = List.of(
            Path.of("src\\main\\java\\edu\\hw9\\task2\\files\\tenFilesDir0"),
            Path.of("src\\main\\java\\edu\\hw9\\task2\\files\\tenFilesDir1"),
            Path.of("src\\main\\java\\edu\\hw9\\task2\\files\\tenFilesDir2"),
            Path.of("src\\main\\java\\edu\\hw9\\task2\\files")
        );
        var recursiveTask = new FindDirectories(path);
        
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            var res = forkJoinPool.invoke(recursiveTask).getKey();

            assertEquals(expected, res);
        }

    }
}
