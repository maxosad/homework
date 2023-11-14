package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {

    private Task7() { }

    public static boolean thirdZero(String string) {
        Pattern pattern = Pattern.compile("[0,1]{2}0[0,1]*");
        return pattern.matcher(string).matches();
    }

    public static boolean startsEndsSame(String string) {
        Pattern pattern = Pattern.compile("(0[0,1]*0)|(1[0,1]*1)");
        return pattern.matcher(string).matches();
    }

    public static boolean sizeOneToThree(String string) {
        Pattern pattern = Pattern.compile("[0,1]{1,3}");
        return pattern.matcher(string).matches();
    }
}
