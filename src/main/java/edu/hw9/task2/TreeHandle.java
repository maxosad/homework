package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TreeHandle {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("src/main/java/edu/hw9/task2/files");
        var recursiveTask = new FindDirectories(path);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            System.out.println(forkJoinPool.invoke(recursiveTask).getKey());
        }
//        Files.list(path)
//            .filter(Files::isRegularFile)
//            .forEach(System.out::println);
    }

}
