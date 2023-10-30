package edu.hw3.Task5;

import java.util.Objects;

public class NameSurname {
    private String name;
    private String surname;

    public NameSurname(String nameSurname) {
        var ns = nameSurname.split(" ");
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameSurname that = (NameSurname) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
