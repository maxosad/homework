package edu.hw5;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void averageTime() {
        var list = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20");

        var result = Task1.countAvg(list);

        assertEquals("3ч 40м", result);
    }

}
