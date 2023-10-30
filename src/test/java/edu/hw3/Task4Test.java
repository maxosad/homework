package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

//    convertToRoman(2) ➞ "II"
//    convertToRoman(12) ➞ "XII"
//    convertToRoman(16) ➞ "XVI"
    @Test
    @DisplayName("example test1")
    void convertToRoman1() {
        assertEquals("II", Task4.convertToRoman(2));
    }

    @Test
    @DisplayName("example test2")
    void convertToRoman2() {
        assertEquals("XII", Task4.convertToRoman(12));
    }

    @Test
    @DisplayName("example test3")
    void convertToRoman3() {
        assertEquals("XVI", Task4.convertToRoman(16));
    }

    @Test
    @DisplayName("four test")
    void convertToRoman4() {
        assertEquals("IV", Task4.convertToRoman(4));
    }

    @Test
    @DisplayName("Illegal argument")
    void illegalTest() {
        var thr = assertThrows(IllegalArgumentException.class,
            () -> Task4.convertToRoman(0));
        assertEquals("number should be more zero and less four thousands",
            thr.getMessage());
    }

}
