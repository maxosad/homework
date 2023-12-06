package edu.hw9.task2;

import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.*;

class FindFilesTest {

    @Test
    void compute() {
        String systemSeparator = FileSystems.getDefault().getSeparator();
        List<String[]> expectedString = List.of(
            new String[]{"src", "main", "java", "edu", "hw9", "task2", "files", "bigPhp.php"},
            new String[]{"src", "main", "java", "edu", "hw9", "task2", "files", "dir2", "php0.php"},
            new String[]{"src", "main", "java", "edu", "hw9", "task2", "files", "dir2", "php1.php"}
        );
        List<Path> expected = expectedString.stream()
            .map(pathNames -> Path.of(String.join(systemSeparator, pathNames)))
            .toList();
        Path path = Path.of("src/main/java/edu/hw9/task2/files");
        FindFiles findFilesRecursiveTask = new FindFiles(path);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            var res = forkJoinPool.invoke(findFilesRecursiveTask);
            Thread.sleep(1000);
            assertEquals(expected, res);
        } catch (InterruptedException e) {
            fail(e);
        }
    }
}
