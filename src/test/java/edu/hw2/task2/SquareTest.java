package edu.hw2.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    private static Square square;

    boolean checkSquareFields(Square square, int target) {
        return square.getHeight() == target && square.getWidth() == target;
    }

    @BeforeEach
    void setUp() {
        square = new Square(4);
    }

    @Test
    void constructorTest() {
        assertEquals(4, square.getHeight());
        assertEquals(4, square.getWidth());
    }

    @Test
    void areaTest() {
        assertEquals(16, square.area());
    }

    @Test
    void setWidth() {
        Square newSquare = square.setWidth(10);
        assertTrue(checkSquareFields(newSquare, 10));
    }

    @Test
    void setHeight() {
        Square newSquare = square.setHeight(10);
        assertTrue(checkSquareFields(newSquare, 10));
    }


}
