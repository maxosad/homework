package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Generator {
    public static void generateDirectoryWithFiles(Path path, String name, int count) {
        try {
            Path directoryPath = Files.createDirectory(path);
            for (int fileCount = 0; fileCount < count; fileCount++) {
                generateFile(directoryPath, 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateFile(Path path, int size) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
