package edu.hw3.Task5;

import java.util.Comparator;

public class WrappedComparator implements Comparator<String> {
    private Comparator<String> baseComparator;

    WrappedComparator(String ascDesc) {
        baseComparator = ascDesc.equals("ASC") ? Comparator.naturalOrder() : Comparator.reverseOrder();
    }


    @Override
    public int compare(String o1, String o2) {
        String[] nameSurename1 = o1.split(" ");
        String[] nameSurename2 = o2.split(" ");
        int nameSurename1Length = nameSurename1.length;
        int nameSurename2Length = nameSurename2.length;

        if (nameSurename1Length < 1
            || nameSurename2Length < 1
            || nameSurename2Length > 2
            || nameSurename1Length > 2) {
            throw new IllegalArgumentException("wrong input");
        }

        String wordToCompare1;
        String wordToCompare2;
        if (nameSurename1Length == 1 || nameSurename2Length == 1) {
            wordToCompare1 = nameSurename1[0];
            wordToCompare2 = nameSurename2[0];
        } else {
            wordToCompare1 = nameSurename1[1];
            wordToCompare2 = nameSurename2[1];
        }
        return baseComparator.compare(wordToCompare1, wordToCompare2);
    }
}
