package edu.hw3;

public class Task1 {
    public static String atbash(String stringToCode) {
        StringBuilder answer = new StringBuilder();
        int stringToCodeSize = stringToCode.length();

        for (int letterIndex = 0; letterIndex < stringToCodeSize; letterIndex++) {
            char c = stringToCode.charAt(letterIndex);
            int sdvig = 0;
            if (c >= 'a' && c <='z') {
                sdvig = c - 'a';
                c = (char) ('z' - sdvig);
            }
            if (c >= 'A' && c <='Z') {
                sdvig = c - 'A';
                c = (char) ('Z' - sdvig);
            }
            answer.append(c);
        }

        return answer.toString();
    }
}
