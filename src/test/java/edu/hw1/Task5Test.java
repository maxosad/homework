package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    @DisplayName("11 -> true")
    void isPalendrom1() {
        assertTrue(Task5.isPalendrom(new ArrayList<>(List.of(new Integer[]{1, 1}))));
    }

    @Test
    @DisplayName("121 -> true")
    void isPalendrom2() {
        assertTrue(Task5.isPalendrom(new ArrayList<>(List.of(new Integer[]{1,2,1}))));
    }

     @Test
    @DisplayName("1214 -> false")
    void isPalendrom3() {
        assertFalse(Task5.isPalendrom(new ArrayList<>(List.of(new Integer[]{1,2,1,4}))));
    }



    @Test
    void integerToArrayList() {
        assertEquals(Task5.integerToArrayList(12345),
                new ArrayList<>(List.of(new Integer[]{1, 2, 3, 4, 5})));
    }

    @Test
    void addNumberByDigits() {
        ArrayList<Integer> origArray = new ArrayList<>(List.of(new Integer[] {1,2,3,4,5}));
        ArrayList<Integer> targetArray = new ArrayList<>(List.of(new Integer[] {1,2,3,4,5,1,1}));

        Task5.addNumberByDigits(11, origArray);
        assertEquals(origArray, targetArray);
    }

    @Test
    @DisplayName("5610 -> 111")
    void sumArray1() {
        ArrayList<Integer> origArray = new ArrayList<>(List.of(new Integer[] {5,6,1,0}));
        ArrayList<Integer> targetArray = new ArrayList<>(List.of(new Integer[] {1,1,1}));

        assertEquals(Task5.sumArray(origArray), targetArray);
    }

    @Test
    @DisplayName("123 -> 33")
    void sumArray2() {
        ArrayList<Integer> origArray = new ArrayList<>(List.of(new Integer[] {1,2,3}));
        ArrayList<Integer> targetArray = new ArrayList<>(List.of(new Integer[] {3,3}));

        assertEquals(Task5.sumArray(origArray), targetArray);
    }

    @Test
    @DisplayName("1234 -> 37")
    void sumArray3() {
        ArrayList<Integer> origArray = new ArrayList<>(List.of(new Integer[] {1,2,3,4}));
        ArrayList<Integer> targetArray = new ArrayList<>(List.of(new Integer[] {3,7}));

        assertEquals(Task5.sumArray(origArray), targetArray);
    }
//    isPalindromeDescendant(11211230) -> true // 11211230 -> 2333 -> 56 -> 11
//    isPalindromeDescendant(13001120) -> true // 13001120 -> 4022 ➞ 44
//    isPalindromeDescendant(23336014) -> true // 23336014 -> 5665
//    isPalindromeDescendant(11) -> true

    @Test
    @DisplayName("11211230 -> true")
    void isPalindromeDescendant1() {
        assertTrue(Task5.isPalindromeDescendant(11211230));
    }

    @Test
    @DisplayName("13001120 -> true")
    void isPalindromeDescendant2() {
        assertTrue(Task5.isPalindromeDescendant(13001120));
    }

    @Test
    @DisplayName("23336014 -> true")
    void isPalindromeDescendant3() {
        assertTrue(Task5.isPalindromeDescendant(23336014));
    }

    @Test
    @DisplayName("11 -> true")
    void isPalindromeDescendant4() {
        assertTrue(Task5.isPalindromeDescendant(11));
    }


}