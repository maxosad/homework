package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @Test
    @DisplayName("0 -> 0")
    void integerInBits1() {
        assertEquals(Task7.integerInBits(0), new ArrayList<>(List.of(new Integer[] {0})));
    }

    @Test
    @DisplayName("1 -> 1")
    void integerInBits2() {
        assertEquals(Task7.integerInBits(1), new ArrayList<>(List.of(new Integer[] {1})));
    }

    @Test
    @DisplayName("2 -> 10")
    void integerInBits3() {
        assertEquals(Task7.integerInBits(2), new ArrayList<>(List.of(new Integer[] {1,0})));
    }



    @Test
    @DisplayName("0 -> 0")
    void bitsInInteger1() {
        assertEquals(Task7.bitsInInteger(new ArrayList<>(List.of(new Integer[] {0}))), 0);
    }

    @Test
    @DisplayName("1 -> 1")
    void bitsInInteger2() {
        assertEquals(Task7.bitsInInteger(new ArrayList<>(List.of(new Integer[] {1}))), 1);
    }

    @Test
    @DisplayName("10 -> 2")
    void bitsInInteger3() {
        assertEquals(Task7.bitsInInteger(new ArrayList<>(List.of(new Integer[] {1,0}))), 2);
    }

    @Test
    @DisplayName("8, 1 -> 4")
    void rotateRight1() {
        assertEquals(Task7.rotateRight(8, 1), 4);
    }

    @Test
    @DisplayName("9, 1 -> 12")
    void rotateRight2() {
        assertEquals(Task7.rotateRight(9, 1), 12);
    }


    @Test
    @DisplayName("17, 1 -> 6")
    void rotateLeft1() {
        assertEquals(Task7.rotateLeft(17, 2), 6);
    }

    @Test
    @DisplayName("16, 1 -> 1")
    void rotateLeft2() {
        assertEquals(Task7.rotateLeft(16, 1), 1);
    }
}