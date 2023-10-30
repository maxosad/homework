package edu.hw3;

public class Task1 {

    private Task1() { }

    public static String atbash(String stringToCode) {
        StringBuilder answer = new StringBuilder();
        int stringToCodeSize = stringToCode.length();

        for (int letterIndex = 0; letterIndex < stringToCodeSize; letterIndex++) {
            char c = stringToCode.charAt(letterIndex);
            if (c >= 'a' && c <= 'z') {
                int sdvig = c - 'a';
                c = (char) ('z' - sdvig);
            }
            if (c >= 'A' && c <= 'Z') {
                int sdvig = c - 'A';
                c = (char) ('Z' - sdvig);
            }
            answer.append(c);
        }

        return answer.toString();
    }
}
