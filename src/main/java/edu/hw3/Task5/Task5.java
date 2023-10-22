package edu.hw3.Task5;

import java.util.Arrays;

public class Task5 {
    private Task5() { }

    public static String[] parseContacts(String[] contacts, String comp) {
        String[] contactsCopy = contacts.clone();
        WrappedComparator wrappedComparator = new WrappedComparator(comp);

        Arrays.sort(contactsCopy, wrappedComparator);
        return contactsCopy;
    }

}
