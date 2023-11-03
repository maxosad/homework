package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Comparator;

public class Task5 {
    private Task5() { }

    public static NameSurname[] parseContacts(String[] contacts, String comp) {
        String[] contactsCopy = contacts.clone();
        Comparator<NameSurname> cmp = Comparator
            .comparing(NameSurname::getSurname)
            .thenComparing(NameSurname::getName);

        return Arrays.stream(contactsCopy)
            .map(NameSurname::new)
            .sorted(comp.equals("DESC") ? Comparator.reverseOrder() : Comparator.naturalOrder())
            .toArray(NameSurname[]::new);
    }
}
