package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearMonteCarloTest {
//    fixString("123456") ➞ "214365"
//    fixString("hTsii  s aimex dpus rtni.g") ➞ "This is a mixed up string."
//    fixString("badce") ➞ "abcde"


    @Test
    void fixStringEx1() {
        assertEquals(Task4.fixString("123456"), "214365");
    }

    @Test
    void fixStringEx2() {
        assertEquals(Task4.fixString("hTsii  s aimex dpus rtni.g"), "This is a mixed up string.");
    }

    @Test
    void fixStringEx3() {
        assertEquals(Task4.fixString("badce"), "abcde");
    }


}
