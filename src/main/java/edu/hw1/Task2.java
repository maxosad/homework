package edu.hw1;

public class Task2 {
    public static Integer countDigits(Integer numberToCountDigits) {
        if (numberToCountDigits == 0)
            return 1;

        Integer countDigits = 0;
        while (numberToCountDigits > 0) {
            countDigits++;
            numberToCountDigits /= 10;
        }
        return countDigits;
    }
}
