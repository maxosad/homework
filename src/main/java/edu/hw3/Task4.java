package edu.hw3;

import java.util.List;

public class Task4 {
    public static String convertToRoman(int intToConvert) {
        return List.of("", "M", "MM", "MMM").get(intToConvert / 1000) +
            List.of("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM").get(intToConvert / 100 % 10) +
            List.of("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC").get(intToConvert / 10 % 10) +
            List.of("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX").get(intToConvert % 10);
    }
}
