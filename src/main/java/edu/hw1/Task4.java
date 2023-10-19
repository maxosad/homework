package edu.hw1;

public class Task4 {

    private Task4() {}

    public static String fixString(String stringToFix) {
        char[] charArr = stringToFix.toCharArray();
        int size = stringToFix.length();
        char tmp;

        for (int i = 1; i < size; i += 2) {
            tmp = charArr[i];
            charArr[i] = charArr[i - 1];
            charArr[i - 1] = tmp;
        }

        return new String(charArr);
    }
}
