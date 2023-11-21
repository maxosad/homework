package edu.hw7;

import java.util.stream.IntStream;

public class Task2 {

    private Task2() { }

    public static int fact(int n) {
        return IntStream.iterate(1, streamInteger -> streamInteger + 1)
            .limit(n)
            .parallel()
            .reduce((firstOperand, secondOperand) -> firstOperand * secondOperand).orElse(0);

    }
}
