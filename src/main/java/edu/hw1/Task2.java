package edu.hw1;

public class Task2 {

    private Task2() {}

    public static Integer countDigits(Integer numberToCountDigits) {
        Integer countDigits = 0;
        Integer localNumberToCountDigits = numberToCountDigits;
        final int TEN = 10;
        do {
            countDigits++;
            localNumberToCountDigits /= TEN;
        } while (localNumberToCountDigits > 0);

        return countDigits;
    }
}
