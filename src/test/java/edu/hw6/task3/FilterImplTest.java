package edu.hw6.task3;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

class FilterImplTest {
    public static final Path dir = Path.of("src/main/java/edu/hw6/task3/files");

    @Test
    void largerThan() {
        List<Path> list = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/task3/files/qwer.txt")
        );

        DirectoryStream.Filter<Path> filter = FilterImpl.largerThan(10);
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            for (var entry : entries) {
                list.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected, list);
    }


    @Test
    void magicNumber() {
        List<Path> list = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/task3/files/qwer.txt")
        );

        DirectoryStream.Filter<Path> filter = FilterImpl.magicNumber( 'P', 'N', 'G');
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            for (var entry : entries) {
                list.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected, list);
    }

    @Test
    void regexContains() {
        List<Path> list = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/task3/files/1.php")
        );

        DirectoryStream.Filter<Path> filter = FilterImpl.regexContains("[1]");
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            for (var entry : entries) {
                list.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected, list);
    }

    @Test
    void largerThenAndGlobeMatches() {
        List<Path> list = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/task3/files/qwer.txt")
        );

        DirectoryStream.Filter<Path> filter = FilterImpl.largerThan(10)
            .and(FilterImpl.globMatches(".+\\.txt"));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            for (var entry : entries) {
                list.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected, list);
    }
}
