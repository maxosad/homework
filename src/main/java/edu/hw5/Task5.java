package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    public static final Pattern VALIDATE_PATTERN = Pattern.compile("^[А-Я]\\d{3}[А-Я]{2}\\d{3}$");
    public static boolean validateNumber(String number) {
        return VALIDATE_PATTERN.matcher(number).find();
    }
}
