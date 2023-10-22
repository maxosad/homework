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
        if (nameSurename1.length != 2 || nameSurename2.length != 2) {
            throw new IllegalArgumentException("String should have two words");
        }
        return baseComparator.compare(nameSurename1[1], nameSurename2[1]);
    }
}
