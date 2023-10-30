package edu.hw3;

import edu.hw3.Task5.NameSurname;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {
    @Test
    @DisplayName("example test1 ")
    void parseContacts1() {
        assertArrayEquals(
            Stream.of("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke")
                .map(NameSurname::new)
                .toArray(NameSurname[]::new),
            Task5.parseContacts(new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC"));
    }

    @Test
    @DisplayName("example test2 ")
    void parseContacts2() {
        assertArrayEquals(
            Stream.of("Carl Gauss", "Leonhard Euler", "Paul Erdos")
                .map(NameSurname::new)
                .toArray(NameSurname[]::new),
            Task5.parseContacts(new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC"));
    }

    @Test
    @DisplayName("example test3 ")
    void emptyArray() {
        assertArrayEquals(new NameSurname[]{},
            Task5.parseContacts(new String[]{}, "DESC"));
    }

    @Test
    @DisplayName("should throw NullPointerException")
    void nullTest() {
        assertThrows(java.lang.NullPointerException.class,
            () -> Task5.parseContacts(null, "DESC"));
    }

    @Test
    @DisplayName("Some words have only Name")
    void nameCompTest() {
        assertArrayEquals(
            Stream.of("Carl Gauss", "Leonhard Euler", "Paul Erdos", "A")
                .map(NameSurname::new)
                .toArray(NameSurname[]::new),
            Task5.parseContacts(new String[]{"Paul Erdos", "A", "Leonhard Euler", "Carl Gauss"}, "DESC"));
    }


}
