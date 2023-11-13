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

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;

    public static final AbstractFilter READABLE = Files::isReadable;
//    public static final AbstractFilter readable = entry -> exp -> Files.size(entry) > exp;

    public static AbstractFilter largerThan(long expectedSize) {
        return entry -> Files.size(entry) > expectedSize;
    }

    public static AbstractFilter globMatches(String pattern) {
        return entry -> entry.toString().matches(pattern);
    }

    public static AbstractFilter magicNumber(char... chars) {
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

    public static AbstractFilter regexContains(String reg) {
        return entry -> {
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(entry.getFileName().toString());
//            System.out.println(entry);
            return matcher.find();
//            entry.getFileName().toString().matches(pattern);
        };
    }
}
