package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {

    private Task8() { }

    public static boolean oddLength(String string) {
        Pattern pattern = Pattern.compile("[0,1]([0,1]{2})*");
        return pattern.matcher(string).matches();
    }

    public static boolean volatileLength(String string) {
        Pattern pattern = Pattern.compile("((0|1[0,1])([0,1]{2})*)");
        return pattern.matcher(string).matches();
    }

    public static boolean zerosCountIsMultipleOfThree(String string) {
        Pattern pattern = Pattern.compile("(1*01*01*01*)*");
        return pattern.matcher(string).matches();
    }

    public static boolean anyBut11Or111(String string) {
        Pattern pattern = Pattern.compile(
            "(0[0,1]*)|(1((0[0,1]*)|(1((0[0,1]*)|(1[0,1]+)))|()))"
        );
        return pattern.matcher(string).matches();
    }

    public static boolean everyOddLetterIsOne(String string) {
        Pattern pattern = Pattern.compile("(1[0,1])*1?");
        return pattern.matcher(string).matches();
    }

    public static boolean moreOneZeroLessTwoOnes(String string) {
        Pattern pattern = Pattern.compile("(0{2,})|(10{2,})|(0{2,}1)|(0+10+)");
        return pattern.matcher(string).matches();
    }

    public static boolean noSequentialOne(String string) {
        Pattern pattern = Pattern.compile("1?(0+1?)*0*");
        return pattern.matcher(string).matches();
    }
}
