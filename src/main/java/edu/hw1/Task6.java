package edu.hw1;

import java.util.*;

import static edu.hw1.Util.integerToArrayList;

public class Task6 {
    public static final Integer KAPREKAR = 6174;
    public static Integer arrayListToInteger(ArrayList<Integer> arrToInteger) {
        Integer ansNumber = 0;

        for (Integer el : arrToInteger) {
            ansNumber *= 10;
            ansNumber += el;
        }
        return ansNumber;
    }

    public static Integer k(Integer number) {
        ArrayList<Integer> numberInArr;
        ArrayList<Integer> sortedMore;
        ArrayList<Integer> sortedLess;
        Integer biggerNumber;
        Integer smallerNumber;

        numberInArr = integerToArrayList(number);
        numberInArr.sort(Integer::compareTo);
        sortedMore = new ArrayList<>(numberInArr);
        numberInArr.sort(Comparator.reverseOrder());
        sortedLess = new ArrayList<>(numberInArr);

        biggerNumber = arrayListToInteger(sortedLess);
        smallerNumber = arrayListToInteger(sortedMore);

        number = biggerNumber - smallerNumber;
        return number;
    }

    public static Integer countK(Integer number) {
        Integer stepsTo6174 = 0;

        while (!number.equals(KAPREKAR)) {
            stepsTo6174++;
            number = k(number);
        }

        return stepsTo6174;
    }
}
