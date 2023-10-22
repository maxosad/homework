package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Comparator;

public class Task5 {

    public static String[] parseContacts(String[] contacts, String comp) {
        String[] contactsCopy = contacts.clone();
        WrappedComparator wrappedComparator = new WrappedComparator(comp);

        Arrays.sort(contactsCopy, wrappedComparator);
        return contactsCopy;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString("John Locke".split(" ")));
        System.out.println(Arrays.toString(Task5.parseContacts(new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC")));
    }
}
