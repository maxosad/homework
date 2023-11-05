package edu.hw5;

import jdk.jshell.spi.ExecutionControl;
import java.util.regex.Pattern;

public class Task8 {
    public static boolean oddLength(String string) {
        Pattern pattern = Pattern.compile("[0,1]([0,1]{2})*");
        return pattern.matcher(string).matches();
    }

    public static boolean volatileLength(String string) {
        Pattern pattern = Pattern.compile("((0|1[0,1])([0,1]{2})*)");
        return pattern.matcher(string).matches();
    }

    public static boolean multipleOfThree(String string) {
        Pattern pattern = Pattern.compile("(01*01*01*)*");
        return pattern.matcher(string).matches();
    }

    public static boolean task4(String string) {
        Pattern pattern = Pattern.compile("(110[0,1]*)|(111[0,1]+)|");
        return false;
//        return pattern.matcher(string).matches();
    }

    public static boolean everyOddLetterIsOne(String string) {
        Pattern pattern = Pattern.compile("1([0,1]1)*");
        return pattern.matcher(string).matches();
    }

    public static boolean noSequentialOne(String string) {
        Pattern pattern = Pattern.compile("0*(10*)");
        return pattern.matcher(string).matches();
    }
}
