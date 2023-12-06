package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TreeHandle {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("src/main/java/edu/hw9/task2/files");
        //39 PHP
//        Path path = Path.of("src/main/java/edu/hw9/task2/files/dir2");
//        var recursiveTask = new FindDirectories(path);
//        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
//            System.out.println(forkJoinPool.invoke(recursiveTask).getKey());
//        } 39
//        Files.list(path)
//            .filter(Files::isRegularFile)
//            .filter(f -> f.endsWith(Path.of(".php")))
//            .forEach(System.out::println);
        var ff = new FindFiles(path);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            System.out.println(forkJoinPool.invoke(ff));
        }
    }

}
