package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    void arrayListToInteger() {
        ArrayList<Integer> arr = new ArrayList<>(List.of(new Integer[] {1,2,3,4,5,6}));
        assertEquals(Task6.arrayListToInteger(arr), 123456);
    }

    @Test
    @DisplayName("6174 -> 6174")
    void k6174() {
        assertEquals(Task6.k(Task6.KAPREKAR), Task6.KAPREKAR);
    }

    @Test
    @DisplayName("3524 -> 3087")
    void k3524() {
        assertEquals(Task6.k(3524), 3087);
    }

    @Test
    @DisplayName("3087 -> 8352")
    void k3087() {
        assertEquals(Task6.k(3087), 8352);
    }

    @Test
    @DisplayName("8352 -> 6174")
    void k8352() {
        assertEquals(Task6.k(8352), Task6.KAPREKAR);
    }

    @Test
    @DisplayName("6621 -> 5")
    void countK6621() {
        assertEquals(Task6.countK(6621), 5);
    }

    @Test
    @DisplayName("6554 -> 4")
    void countK6554() {
        assertEquals(Task6.countK(6554), 4);
    }

    @Test
    @DisplayName("1234 -> 3")
    void countK1234() {
        assertEquals(Task6.countK(1234), 3);
    }

    @Test
    @DisplayName("6174 -> 0")
    void countK6174() {
        assertThat(Task6.countK(Task6.KAPREKAR)).isZero();
    }

}