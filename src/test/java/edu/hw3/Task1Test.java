package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    @DisplayName("only LowerCase english letter")
    void atbash1() {
        assertEquals("svool dliow", Task1.atbash("hello world"));
    }

    @Test
    @DisplayName("only UpperCase english letter")
    void atbash2() {
        assertEquals("SVOOL DLIOW", Task1.atbash("HELLO WORLD"));
    }

    @Test
    @DisplayName("no english letter")
    void atbash3() {
        String withoutLatinLetters = "12345678!,.";
        assertEquals(withoutLatinLetters, Task1.atbash(withoutLatinLetters));
    }

    @Test
    @DisplayName("test from example1")
    void atbash4() {
        assertEquals("Svool dliow!", Task1.atbash("Hello world!"));
    }

    @Test
    @DisplayName("test from example2")
    void atbash5() {
        assertEquals("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
            Task1.atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler"));
    }
}
