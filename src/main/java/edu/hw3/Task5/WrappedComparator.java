package edu.hw3.Task5;

import java.util.Comparator;

public class WrappedComparator implements Comparator<String> {
    private final Comparator<String> baseComparator;

    WrappedComparator(String ascDesc) {
        baseComparator = ascDesc.equals("ASC") ? Comparator.naturalOrder() : Comparator.reverseOrder();
    }


    @Override
    public int compare(String o1, String o2) {
        String[] nameSurname1 = o1.split(" ");
        String[] nameSurname2 = o2.split(" ");
        int nameSurname1Length = nameSurname1.length;
        int nameSurname2Length = nameSurname2.length;

        if (nameSurname1Length < 1
            || nameSurname2Length < 1
            || nameSurname2Length > 2
            || nameSurname1Length > 2) {
            throw new IllegalArgumentException("wrong input");
        }

        String wordToCompare1;
        String wordToCompare2;
        if (nameSurname1Length == 1 || nameSurname2Length == 1) {
            wordToCompare1 = nameSurname1[0];
            wordToCompare2 = nameSurname2[0];
        } else {
            wordToCompare1 = nameSurname1[1];
            wordToCompare2 = nameSurname2[1];
        }
        return baseComparator.compare(wordToCompare1, wordToCompare2);
    }
}
