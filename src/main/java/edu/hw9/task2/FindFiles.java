package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


public class FindFiles extends RecursiveTask<List<Path>> {
    public static final long FILE_SIZE = 39L;
    public static final String FILE_TYPE = ".php";
    private final Path path;

    public FindFiles(Path path) {
        this.path = path;
    }

    @Override
    protected List<Path> compute() {
        try (var str = Files.list(path)) {
            List<Path> dirFileList = str.toList();
            List<Path> acceptedFiles = new ArrayList<>();
            List<FindFiles> recursiveTasksList = new ArrayList<>();

            for (Path dirFilePath : dirFileList) {
                if (Files.isRegularFile(dirFilePath)
                    && Files.size(dirFilePath) > FILE_SIZE
                    && dirFilePath.toString().endsWith(FILE_TYPE)) {
                    acceptedFiles.add(dirFilePath);
                } else if (Files.isDirectory(dirFilePath)) {
                    recursiveTasksList.add(new FindFiles(dirFilePath));
                }
            }

            for (var task : recursiveTasksList) {
                task.fork();
            }

            for (var task : recursiveTasksList) {
                acceptedFiles.addAll(task.join());
            }

            return acceptedFiles;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
