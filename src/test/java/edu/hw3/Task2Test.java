package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    @DisplayName("example test1")
    void clusterize1() {
        assertEquals(new ArrayList<>(List.of(new String[] {"()", "()", "()"})), Task2.clusterize("()()()"));
    }

    @Test
    @DisplayName("example test2")
    void clusterize2() {
        assertEquals(new ArrayList<>(List.of(new String[] {"((()))"})), Task2.clusterize("((()))"));
    }

    @Test
    @DisplayName("example test3")
    void clusterize3() {
        assertEquals(new ArrayList<>(List.of(new String[] {"((()))", "(())", "()", "()", "(()())"})),
            Task2.clusterize("((()))(())()()(()())"));
    }

    @Test
    @DisplayName("example test4")
    void clusterize4() {
        assertEquals(new ArrayList<>(List.of(new String[] {"((())())", "(()(()()))"})),
            Task2.clusterize("((())())(()(()()))"));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException ")
    void balanceLessZeroInMiddle() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("())("));
        assertEquals(Util.BALANCE_LESS_ZERO_MESSAGE, thrown.getMessage());

    }

    @Test
    @DisplayName("Should throw IllegalArgumentException ")
    void endBalanceNotEqualZero1() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("()()("));
        assertEquals(Util.BALANCE_NOT_ZERO_MESSAGE, thrown.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException ")
    void endBalanceNotEqualZero2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("()())"));
        assertEquals(Util.BALANCE_LESS_ZERO_MESSAGE, thrown.getMessage());
    }

}
