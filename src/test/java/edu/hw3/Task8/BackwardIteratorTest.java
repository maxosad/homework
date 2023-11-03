package edu.hw3.Task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BackwardIteratorTest {

    private void commonPart(BackwardIterator<Integer> bi) {
        assertTrue(bi.hasNext());
        assertEquals(3, bi.next());
        assertTrue(bi.hasNext());
        assertEquals(2, bi.next());
        assertTrue(bi.hasNext());
        assertThat(bi.next()).isOne();
        assertFalse(bi.hasNext());
    }

    @Test
    @DisplayName("List as collection")
    void listTest() {
        BackwardIterator<Integer> bi = new BackwardIterator<>(List.of(1,2,3));
        commonPart(bi);
    }

    @Test
    @DisplayName("Queue as collection")
    void queueTest() {
        Deque<Integer> deque = new ArrayDeque<>(List.of(new Integer[]{1,2,3}));
        BackwardIterator<Integer> bi = new BackwardIterator<>(deque);
        commonPart(bi);
    }

    @Test
    @DisplayName("Should throw NoSuchElementException")
    void nextTest() {
        Deque<Integer> deque = new ArrayDeque<>(List.of(new Integer[]{}));
        BackwardIterator<Integer> bi = new BackwardIterator<>(deque);
        assertThrows(NoSuchElementException.class, bi::next);
    }
}
