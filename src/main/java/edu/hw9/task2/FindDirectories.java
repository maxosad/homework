package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FindDirectories extends RecursiveTask<AbstractMap.SimpleEntry<List<Path>, Long>> {
    public static final long COUNT_FILE_ALLOWED = 10L;
    private final Path path;

    public FindDirectories(Path path) {
        this.path = path;
    }

    @Override
    protected AbstractMap.SimpleEntry<List<Path>, Long> compute() {
        try (var dirFileStream = Files.list(path)) {
            long countFiles = 0L;
            List<Path> dirList = new ArrayList<>();
            List<FindDirectories> dirRecursiveTaskList = new ArrayList<>();
            var dirFilesList = dirFileStream.toList();

            for (Path dirFilepath : dirFilesList) {
                if (Files.isRegularFile(dirFilepath)) {
                    countFiles++;
                } else if (Files.isDirectory(dirFilepath)) {
                    dirRecursiveTaskList.add(new FindDirectories(dirFilepath));
                }
            }
            for (var task : dirRecursiveTaskList) {
                task.fork();
            }

            for (var task : dirRecursiveTaskList) {
                var listCountEntry = task.join();
                dirList.addAll(listCountEntry.getKey());
                countFiles += listCountEntry.getValue();
            }

            if (countFiles >= COUNT_FILE_ALLOWED) {
                dirList.add(path);
            }
            return new AbstractMap.SimpleEntry<>(dirList, countFiles);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
