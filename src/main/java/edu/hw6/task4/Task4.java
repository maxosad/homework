package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

@SuppressWarnings({"uncommentedmain", "NestedTryDepth"})
public class Task4 {
    private Task4() { }

    public static void main(String[] args) {
        Path file = Path.of("src/main/java/edu/hw6/task4/files/input.txt");
        try (var oos = Files.newOutputStream(file)) {
            try (CheckedOutputStream cos = new CheckedOutputStream(oos, new Adler32())) {
                try (BufferedOutputStream bos = new BufferedOutputStream(cos)) {
                    try (OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8)) {
                        try (PrintWriter pw = new PrintWriter(osw)) {
                            osw.write("Programming is learned by writing programs. ― Brian Kernighan");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
