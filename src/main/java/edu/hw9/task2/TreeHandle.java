package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TreeHandle {
    public static void main(String[] args) {
//        RecursiveTask<Integer> recursiveTask = ;
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String path = "src/main/java/edu/hw9/task2/files";
        try {
            Files.list(Path.of(path)).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
