package edu.hw1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void integerToArrayList() {
        assertEquals(Util.integerToArrayList(12345),
                new ArrayList<>(List.of(new Integer[]{1, 2, 3, 4, 5})));
    }
}