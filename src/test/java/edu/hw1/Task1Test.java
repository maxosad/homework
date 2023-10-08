package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void takeZeroShouldReturnZero() {
        assertEquals(Task1.minutesToSeconds("00:00"),
                0
        );
    }

    @Test
    void takeOneMinuteShouldReturnSixtySeconds() {
        assertEquals(Task1.minutesToSeconds("01:00"),
                60
        );
    }

    @Test
    void takeMinsAndSecs() {
        assertEquals(Task1.minutesToSeconds("13:56"),
                836
        );
    }

    @Test
    void takeSixtySecondsShouldReturnMinusOne() {
        assertEquals(Task1.minutesToSeconds("10:60"),
                -1
        );
    }

}