package edu.hw3;

import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

//
//    parseContacts([], "DESC") ➞ []
//    parseContacts(null, "DESC") ➞ []
    @Test
    @DisplayName("example test1 ")
    void parseContacts1() {
        assertArrayEquals(new String[]{"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"},
            Task5.parseContacts(new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC"));
    }

    @Test
    @DisplayName("example test2 ")
    void parseContacts2() {
        assertArrayEquals(new String[]{"Carl Gauss", "Leonhard Euler", "Paul Erdos"},
            Task5.parseContacts(new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC"));
    }

    @Test
    @DisplayName("example test3 ")
    void emptyArray() {
        assertArrayEquals(new String[]{},
            Task5.parseContacts(new String[]{}, "DESC"));
    }

    @Test
    @DisplayName("should throw NullPointerException")
    void nullTest() {
        assertThrows(java.lang.NullPointerException.class,
            () -> Task5.parseContacts(null, "DESC"));
    }

    @Test
    @DisplayName("Some words hav only Name")
    void nameCompTest() {
        assertArrayEquals(new String[]{"Carl Gauss", "Leonhard Euler", "Paul Erdos", "A"},
            Task5.parseContacts(new String[]{"Paul Erdos", "A", "Leonhard Euler", "Carl Gauss"}, "DESC"));
    }


}
