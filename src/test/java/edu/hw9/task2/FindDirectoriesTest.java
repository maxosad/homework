package edu.hw9.task2;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindDirectoriesTest {

    @Test
    void compute() {
        Path path = Path.of("src/main/java/edu/hw9/task2/files");
        String systemSeparator = FileSystems.getDefault().getSeparator();
        List<String[]> expectedString = List.of(
            new String[]{"src", "main", "java", "edu", "hw9", "task2", "files", "tenFilesDir0"},
            new String[]{"src", "main", "java", "edu", "hw9", "task2", "files", "tenFilesDir1"},
            new String[]{"src", "main", "java", "edu", "hw9", "task2", "files", "tenFilesDir2"},
            new String[]{"src", "main", "java", "edu", "hw9", "task2", "files"}
        );
        List<Path> expected = expectedString.stream()
            .map(pathNames -> Path.of(String.join(systemSeparator, pathNames)))
            .toList();
        var recursiveTask = new FindDirectories(path);

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            var res = forkJoinPool.invoke(recursiveTask).getKey();
            System.out.println(res);
            assertEquals(expected, res);
        }

    }
}
