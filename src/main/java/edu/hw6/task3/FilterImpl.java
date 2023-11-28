package edu.hw6.task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterImpl {
    private FilterImpl() { }

    public static Filter largerThan(long expectedSize) {
        return entry -> Files.size(entry) > expectedSize;
    }

    public static Filter globMatches(String pattern) {
        return entry -> entry.toString().matches(pattern);
    }

    public static Filter magicNumber(char... chars) {
        final int charsLength = chars.length;
        return entry -> {
            char[] bytes = new char[charsLength];

           try (BufferedReader fis = new BufferedReader(new FileReader(entry.toFile()))) {
               fis.read(bytes, 0, charsLength);
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
           return Arrays.equals(chars, bytes);
        };
    }

    public static Filter regexContains(String reg) {
        return entry -> {
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(entry.getFileName().toString());
            return matcher.find();
        };
    }
}
