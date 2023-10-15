package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Util {
    public static final Integer TEN = 10;
    public static final Integer ZERO = 0;
    public static final int MINUS_ONE = -1;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int MINUS_TWO = -2;

    private Util() {}

    public static ArrayList<Integer> integerToArrayList(Integer numberToArr) {
        ArrayList<Integer> ansArr = new ArrayList<>();
        Integer localNumberToArr = numberToArr;
        do {
            ansArr.add(localNumberToArr % TEN);
            localNumberToArr /= TEN;
        } while (!localNumberToArr.equals(ZERO));

        Collections.reverse(ansArr);

        return ansArr;
    }
}
