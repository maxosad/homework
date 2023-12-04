package edu.hw6.task5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HackerNewsTest {

    @Test
    void news() {
        String expected = "JDK 21 Release Note";
        long id1 = 37570526;
        long id2 = 37570037;

        String res1 = HackerNews.news(id1);
        String res2 = HackerNews.news(id2);

        assertEquals(expected, res1);
        assertEquals(expected, res2);
    }
}
