package edu.hw2.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantTest {

    @Test
    void evaluate() {
        var two = new Constant(2.0);
        assertEquals(2.0, two.evaluate());
    }
}
