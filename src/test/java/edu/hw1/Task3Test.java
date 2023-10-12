package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    void minMax() {
        Integer[] arr = new Integer[] {-1,2,0,0,5,6};
        Task3.Pair minMax = Task3.minMax(arr);
        assertEquals(minMax.getMin(), -1);
        assertEquals(minMax.getMax(), 6);
    }
//    isNestable([1, 2, 3, 4], [0, 6]) -> true
//    isNestable([3, 1], [4, 0]) -> true
//    isNestable([9, 9, 8], [8, 9]) -> false
//    isNestable([1, 2, 3, 4], [2, 3]) -> false
    @Test
    void isNestableEx1True() {
        assertEquals(Task3.isNestable(new Integer[] {1, 2, 3, 4}, new Integer[] {0, 6}), true);
    }

    @Test
    void isNestableEx2True() {
        assertEquals(Task3.isNestable(new Integer[] {3, 1}, new Integer[] {4, 0}), true);
    }

    @Test
    void isNestableEx3False() {
        assertEquals(Task3.isNestable(new Integer[] {9, 9, 8}, new Integer[] {8, 9}), false);
    }

    @Test
    void isNestableEx4False() {
        assertEquals(Task3.isNestable(new Integer[] {1, 2, 3, 4}, new Integer[] {2, 3}), false);
    }

    @Test
    void isNestableEmptyArr() {
        assertEquals(Task3.isNestable(new Integer[] {}, new Integer[] {}), false);
    }


}