package edu.hw9.task2;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.*;

class FindFilesTest {

    @Test
    void compute() {
        List<Path> expected = List.of(
            Path.of("src\\main\\java\\edu\\hw9\\task2\\files\\bigPhp.php"),
            Path.of("src\\main\\java\\edu\\hw9\\task2\\files\\dir2\\php0.php"),
            Path.of("src\\main\\java\\edu\\hw9\\task2\\files\\dir2\\php1.php")
        );
        Path path = Path.of("src/main/java/edu/hw9/task2/files");
        FindFiles findFilesRecursiveTask = new FindFiles(path);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            var res = forkJoinPool.invoke(findFilesRecursiveTask);
            assertEquals(expected, res);
        }
    }
}
