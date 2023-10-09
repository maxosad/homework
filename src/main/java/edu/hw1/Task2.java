package edu.hw1;

public class Task2 {
    public static Integer countDigits(Integer numberToCountDigits) {
        Integer countDigits = 0;

        do {
            countDigits++;
            numberToCountDigits /= 10;
        } while (numberToCountDigits > 0);

        return countDigits;
    }
}
