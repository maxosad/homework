package edu.hw3.Task6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.PriorityQueue;
import static org.junit.jupiter.api.Assertions.*;

class StockMarketImplTest {
    private static StockMarketImpl stockMarket;
    @BeforeEach
    void setUp() {
        stockMarket = new StockMarketImpl();
        stockMarket.add(new Stock("s1", 1));
        stockMarket.add(new Stock("s2", 2));
        stockMarket.add(new Stock("s3", 3));
    }

    @Test
    void mostValuableStock() {
        Stock s8 = new Stock("s9", 7);
        Stock s9 = new Stock("s9", 7);
        stockMarket.add(s8);
        assertEquals(s9, stockMarket.mostValuableStock());
    }

    @Test
    void mostValuableStock2() {
        Stock s8 = new Stock("s9", 7);
        Stock s1 = new Stock("s1", 1);
        Stock s9 = new Stock("s9", 7);
        stockMarket.add(s8);
        stockMarket.add(s1);
        stockMarket.add(s1);
        stockMarket.add(s1);
        assertEquals(s9, stockMarket.mostValuableStock());
    }

    @Test
    void mostValuableStock3() {
        Stock s8 = new Stock("s9", 7);
        Stock s9 = new Stock("s9", 7);
        stockMarket.add(s8);
        stockMarket.add(s8);
        stockMarket.remove(s8);

        assertEquals(s9, stockMarket.mostValuableStock());
    }
}
