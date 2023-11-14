package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    public static final Pattern VALIDATE_PATTERN = Pattern.compile("~|!|@|#|\\$|%|\\^|&|\\*|\\|");

    private Task4() { }

    public static boolean validatePass(String pass) {
        return VALIDATE_PATTERN.matcher(pass).find();
    }
}
