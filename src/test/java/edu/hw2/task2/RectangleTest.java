package edu.hw2.task2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    private static Rectangle rectangle;

    @BeforeEach
    void setUp() {
        rectangle = new Rectangle(2, 2);
    }

    @Test
    void setWidth() {
        Rectangle newRectangle = rectangle.setWidth(4);
        assertEquals(4, newRectangle.getWidth());
    }

    @Test
    void setHeight() {
        Rectangle newRectangle = rectangle.setHeight(4);
        assertEquals(4, newRectangle.getHeight());
    }

    @Test
    void area() {
        assertEquals(4, rectangle.area());
    }
}
