package edu.hw1;

public class Task2 {
    public static Integer countDigits(Integer numberToCountDigits) {
        Integer countDigits = 0;
        final int TEN = 10;
        do {
            countDigits++;
            numberToCountDigits /= TEN;
        } while (numberToCountDigits > 0);

        return countDigits;
    }
}
