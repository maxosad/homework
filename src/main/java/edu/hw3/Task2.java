package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static List<String> clusterize(String parenthesesString) {
        List<String> ansList = new ArrayList<>();
        StringBuilder temporaryParentheses = new StringBuilder();
        int balance = 0;

        int parenthesesStringSize = parenthesesString.length();
        for (int parenthesesIndex = 0; parenthesesIndex < parenthesesStringSize; parenthesesIndex++) {
            char parentheses = parenthesesString.charAt(parenthesesIndex);

            if (parentheses == '(') {
                balance++;
            } else {
                balance--;
            }

            temporaryParentheses.append(parentheses);

            if (balance < 0) {
                throw new IllegalArgumentException(Util.BALANCE_LESS_ZERO_MESSAGE);
            }
            if (balance == 0) {
                ansList.add(temporaryParentheses.toString());
                temporaryParentheses.setLength(0);
            }
        }
        if (balance != 0) {
            throw new IllegalArgumentException(Util.BALANCE_NOT_ZERO_MESSAGE);
        }

        return ansList;
    }
}
