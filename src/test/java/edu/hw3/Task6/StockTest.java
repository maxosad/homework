package edu.hw3.Task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void testEquals() {
        Stock s1 = new Stock("1", 1);
        Stock s2 = new Stock("1", 1);
        assertEquals(s1, s2);
    }

    @Test
    void testEquals2() {
        Stock s1 = new Stock("1", 1);
        Stock s2 = new Stock("1", 1);
        assertTrue(s1.equals(s2));
    }
}
