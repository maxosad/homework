package edu.hw9.task2;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FindDirectoriesTest {

    @Test
    void compute() {
        Path path = Path.of("src/test/java/edu/hw9/files");
        String systemSeparator = FileSystems.getDefault().getSeparator();
        List<String[]> expectedString = List.of(
            new String[]{"src", "test", "java", "edu", "hw9", "files", "tenFilesDir0"},
            new String[]{"src", "test", "java", "edu", "hw9", "files", "tenFilesDir1"},
            new String[]{"src", "test", "java", "edu", "hw9", "files", "tenFilesDir2"},
            new String[]{"src", "test", "java", "edu", "hw9", "files"}
        );
        List<Path> expected = expectedString.stream()
            .map(pathNames -> Path.of(String.join(systemSeparator, pathNames)))
            .toList();
        var recursiveTask = new FindDirectories(path);

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            var res = forkJoinPool.invoke(recursiveTask).getKey();
            Thread.sleep(1000);
            assertEquals(expected, res);
        } catch (InterruptedException e) {
            fail(e);
        }

    }
}
