package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Task5 {
    public static Boolean isPalendrom(ArrayList<Integer> arrToCheckOnPalindrome) {
        int size = arrToCheckOnPalindrome.size();
        if (size <= 1) return false;
        for (int i = 0; i < size/2; i++) {
            if (!arrToCheckOnPalindrome.get(i).equals(arrToCheckOnPalindrome.get(size-1-i)))
                return false;
        }
        return true;
    }

    public static ArrayList<Integer> integerToArrayList(Integer numberToArr) {
        ArrayList<Integer> ansArr = new ArrayList<>();

        do {
            ansArr.add(numberToArr%10);
            numberToArr /= 10;
        } while(numberToArr != 0);

        Collections.reverse(ansArr);

        return ansArr;
    }

    public static void addNumberByDigits(Integer number, ArrayList<Integer> arr) {
        ArrayList<Integer> arrToAdd = integerToArrayList(number);
        arr.addAll(arrToAdd);
    }

    public static ArrayList<Integer> sumArray(ArrayList<Integer> arrToSum) {
        ArrayList<Integer> summedArray = new ArrayList<>();
        int size = arrToSum.size();
        for (int i = 0; i < size; i+=2) {
            Integer pairSum = arrToSum.get(i);
            Integer nextElemInArr = 0;
            try {
                nextElemInArr = arrToSum.get(i+1);
            } catch (IndexOutOfBoundsException ignored) {}
            pairSum += nextElemInArr;
            addNumberByDigits(pairSum, summedArray);
        }
        return summedArray;
    }

    public static Boolean isPalindromeDescendant(Integer integerToCheckOnPalindrome) {
        ArrayList<Integer> numberInArray = integerToArrayList(integerToCheckOnPalindrome);

        while (numberInArray.size() >= 2) {
            if (isPalendrom(numberInArray))
                return true;
            numberInArray = sumArray(numberInArray);
        }
        return false;
    }


}
