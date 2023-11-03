package edu.hw3.Task5;

import java.util.Comparator;
import java.util.Objects;

public class NameSurname implements Comparable<NameSurname> {
    private String name;
    private String surname;

    public NameSurname(String nameSurname) {
        var ns = nameSurname.split(" ");
        if (ns.length > 2) {
            throw new IllegalArgumentException("wrong input");
        }

        name = ns[0];
        surname = ns.length == 2 ? ns[1] : "";

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NameSurname that = (NameSurname) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public int compareTo(NameSurname o) {
        return Comparator
            .comparing(NameSurname::getSurname)
            .thenComparing(NameSurname::getName)
            .compare(this, o);
    }
}
