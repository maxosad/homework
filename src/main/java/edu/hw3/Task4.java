package edu.hw3;

import java.util.List;

public class Task4 {

    private Task4() { }

    public static String convertToRoman(int intToConvert) {
        return List.of("", "M", "MM", "MMM").get(intToConvert / Util.THOUSAND)
            + List.of("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
            .get(intToConvert / Util.HUNDRED % Util.TEN)
            + List.of("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
            .get(intToConvert / Util.TEN % Util.TEN)
            + List.of("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
            .get(intToConvert % Util.TEN);
    }
}
