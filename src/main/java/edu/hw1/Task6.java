package edu.hw1;

import java.util.ArrayList;
import java.util.Comparator;
import static edu.hw1.Util.integerToArrayList;

public class Task6 {
    public static final Integer KAPREKAR = 6174;

    private Task6() {}

    public static Integer arrayListToInteger(ArrayList<Integer> arrToInteger) {
        Integer ansNumber = 0;

        for (Integer el : arrToInteger) {
            ansNumber *= Util.TEN;
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

        return biggerNumber - smallerNumber;
    }

    public static Integer countK(Integer number) {
        Integer stepsTo6174 = 0;
        Integer localNumber = number;
        while (!localNumber.equals(KAPREKAR)) {
            stepsTo6174++;
            localNumber = k(localNumber);
        }

        return stepsTo6174;
    }
}
