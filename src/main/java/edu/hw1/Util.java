package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Util {
    public static ArrayList<Integer> integerToArrayList(Integer numberToArr) {
        ArrayList<Integer> ansArr = new ArrayList<>();

        do {
            ansArr.add(numberToArr%10);
            numberToArr /= 10;
        } while(numberToArr != 0);

        Collections.reverse(ansArr);

        return ansArr;
    }
}
