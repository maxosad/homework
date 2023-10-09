package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task8Test {

    public static int[][] firstBoard = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
    };

    public static int[][] secondBoard = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
    };

    public static int[][] thirdBoard = new int[][] {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
    };

    @Test
    @DisplayName("firstBoard -> true")
    void knightBoardCapture1() {
        assertTrue(Task8.knightBoardCapture(firstBoard));
    }

    @Test
    @DisplayName("secondBoard -> false")
    void knightBoardCapture2() {
        assertFalse(Task8.knightBoardCapture(secondBoard));
    }

    @Test
    @DisplayName("thirdBoard -> false")
    void knightBoardCapture3() {
        assertFalse(Task8.knightBoardCapture(thirdBoard));
    }



    @Test
    @DisplayName("0..8, 0..8")
    void checkRangeOfBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertTrue(Task8.checkRange(i, j));
            }
        }
    }

    @Test
    void checkRangeOutOfBoard() {
        for (int i = -8; i < -1; i++) {
            for (int j = 9; j < 11; j++) {
                assertFalse(Task8.checkRange(i, j));
            }
        }
    }

    @Test
    void checkKnight1() {
        Task8.theBoard = firstBoard;
        assertTrue(Task8.checkKnight(0, 3));
    }
}