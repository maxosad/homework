package edu.hw6.task3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FilterImpl {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;
//    public static final AbstractFilter readable = entry -> exp -> Files.size(entry) > exp;


    public static AbstractFilter largerThan(long expectedSize) {
        return entry -> Files.size(entry) > expectedSize;
    }

    public static AbstractFilter globMatches(String pattern) {
        return entry -> entry.toString().matches(pattern);
    }

    public static AbstractFilter magicNumber(char ...chars) {
        final int charsLength = chars.length;
        return entry -> {
            char[] bytes = new char[charsLength];

           try(BufferedReader fis = new BufferedReader(new FileReader(entry.toFile()))) {
               fis.read(bytes, 0, charsLength);
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
           return Arrays.equals(chars, bytes);
        };
    }

    public static AbstractFilter regexContains(String pattern) {
        return entry -> entry.getFileName().toString().matches(pattern);
    }

    public static void main(String[] args) {



//        DirectoryStream.Filter<Path> filter = regularFile
//            .and(readable)
//            .and(largerThan(100_000))
//            .and(magicNumber( 'P', 'N', 'G'))
//            .and(globMatches("*.png"))
//            .and(regexContains("[-]"));
//
//        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
//            entries.forEach(System.out::println);
//        }

    }
}
