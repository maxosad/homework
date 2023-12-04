package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {
    private Map<String, String> mapForTest;

    @BeforeEach
    void setUp() {
        mapForTest = new DiskMap();
        mapForTest.put("max1", "o");
    }
    @Test
    void size() {
        assertThat(mapForTest.size()).isOne();

    }

    @Test
    void get() {
        var result = mapForTest.get("max1");

        assertEquals("o", result);
    }

    @Test
    void rewrite() {
        mapForTest.put("max1", "w");

        var result = mapForTest.get("max1");

        assertEquals("w", result);
        assertThat(mapForTest.size()).isOne();
    }

    @Test
    void clear() {
        mapForTest.put("max2", "w");
        mapForTest.clear();

        var result = mapForTest.size();

        assertThat(result).isZero();
    }
}
