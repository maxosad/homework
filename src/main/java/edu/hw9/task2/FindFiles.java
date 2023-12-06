package edu.hw9.task2;

import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FindFiles extends RecursiveTask<List<Path>> {
    private final Path path;
    public static final Predicate<Path> PREDICATE = null;


    public FindFiles(Path path) {
        this.path = path;
    }
    @Override
    protected List<Path> compute() {
        return null;
    }
}
