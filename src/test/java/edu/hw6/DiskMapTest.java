package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {
    private Map<String, String> a;

    @BeforeEach
    void setUp() {
        a = new DiskMap();
        a.put("max1", "o");
    }
    @Test
    void size() {
        assertThat(a.size()).isOne();

    }

    @Test
    void get() {
        var result = a.get("max1");

        assertEquals("o", result);
    }

    @Test
    void reWrite() {
        a.put("max1", "w");

        var result = a.get("max1");

        assertEquals("w", result);
        assertThat(a.size()).isOne();
    }

    @Test
    void clear() {
        a.put("max2", "w");
        a.clear();

        var result = a.size();

        assertThat(result).isZero();
    }
}
