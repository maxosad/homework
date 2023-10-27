package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.project1.Session.GIVE_UP_PHRASE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Session session;
    private Integer positionsToGuess;
    private static final String ANACONDA = "anaconda";

    @BeforeEach
    void setUp() {
        AbstractDictionary dictionary = new FixedDictionary(ANACONDA);
        positionsToGuess = dictionary.getWord().length();
        Game game = new Game(5, dictionary);
        session = game.getSession();
    }

    @Test
    @DisplayName("incorrect word")
    void shouldThrowIllegalArgumentException() {
        AbstractDictionary dictionary = new FixedDictionary("");
        positionsToGuess = dictionary.getWord().length();
        assertThrows(IllegalArgumentException.class, () -> new Game(5, dictionary));
    }

    @Test
    @DisplayName("initial State")
    void initialTest() {
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
        assertEquals(List.of(new Character[]{'*', '*', '*', '*', '*', '*', '*', '*'}),
                session.getCurrentWord());
        assertEquals(ANACONDA.length(), session.getPositionsToGuess());
        assertThat(session.getCurrentAttempt()).isZero();
    }

    @Test
    @DisplayName("two letter input")
    void stateShouldNotChange() {
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
        ArrayList<Character> currentWord = session.getCurrentWord();

        AnswerStatus answerStatus = session.checkAnswer("an");

        assertEquals(AnswerStatus.INCORRECT_INPUT, answerStatus);
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
        assertEquals(ANACONDA.length(), session.getPositionsToGuess());
        assertThat(session.getCurrentAttempt()).isZero();
        assertEquals(currentWord, session.getCurrentWord());
    }

    @Test
    @DisplayName("wrong answer scenario")
    void wrongAnswerScenario() {
        ArrayList<Character> currentWord = session.getCurrentWord();
        session.checkAnswer("q");
        assertThat(session.getCurrentAttempt()).isOne();
        assertEquals(currentWord, session.getCurrentWord());
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
    }

    @Test
    @DisplayName("1 new correct letter")
    void correctAnswerScenario1() {
        ArrayList<Character> currentWord = session.getCurrentWord();
        session.checkAnswer("c");
        assertThat(session.getCurrentAttempt()).isZero();
        assertEquals(List.of(new Character[]{'*', '*', '*', 'c', '*', '*', '*', '*'}),
                session.getCurrentWord());
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
    }

    @Test
    @DisplayName("3 new correct letter")
    void correctAnswerScenario2() {
        ArrayList<Character> currentWord = session.getCurrentWord();
        session.checkAnswer("a");
        assertThat(session.getCurrentAttempt()).isZero();
        assertEquals(List.of(new Character[]{'a', '*', 'a', '*', '*', '*', '*', 'a'}),
                session.getCurrentWord());
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
    }

    int currentAttempt = 0;
    private void guessSameWrongLetter(int att) {
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
        ArrayList<Character> currentWord = session.getCurrentWord();
        AnswerStatus answerStatus = session.checkAnswer("q");
        if (att == 0) {
            assertEquals(AnswerStatus.WRONG, answerStatus);
            currentAttempt++;
        } else {
            assertEquals(AnswerStatus.REPEATED_WRONG_LETTER, answerStatus);
        }
        assertEquals(currentAttempt, session.getCurrentAttempt());
        assertEquals(currentWord, session.getCurrentWord());
    }

    private void guessDiffWrongLetter(int id) {
        String[] cs = new String[]{"q", "w", "e", "r", "t", "y", "u"};
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
        ArrayList<Character> currentWord = session.getCurrentWord();
        AnswerStatus answerStatus = session.checkAnswer(cs[id]);
        assertEquals(AnswerStatus.WRONG, answerStatus);
        currentAttempt++;
        assertEquals(currentAttempt, session.getCurrentAttempt());
        assertEquals(currentWord, session.getCurrentWord());
    }

    @Test
    @DisplayName("losing scenario")
    void runOutOfAttempts() {
        for (int i = 0; i < 5; i++) {
            guessDiffWrongLetter(i);
        }
        assertEquals(GameStatus.LOST, session.getStatus());
    }

    @Test
    @DisplayName("re_enter wrong letter scenario")
    void gameShouldBeInProgress() {
        for (int i = 0; i < 5; i++) {
            guessSameWrongLetter(i);
        }
        assertEquals(GameStatus.IN_PROGRESS, session.getStatus());
    }

    @Test
    @DisplayName("winning scenario")
    void winningScenario() {
        AnswerStatus answerStatus;
        for (int i = 0; i < ANACONDA.length() - 1; i++) {
            answerStatus = session.checkAnswer(String.valueOf(ANACONDA.charAt(i)));
            assertEquals(AnswerStatus.CORRECT, answerStatus);
            assertThat(session.getCurrentAttempt()).isZero();
        }

        assertEquals(GameStatus.WIN, session.getStatus());
    }

    @Test
    @DisplayName("give up scenario")
    void gaveUpScenario() {
        AnswerStatus answerStatus = session.checkAnswer(GIVE_UP_PHRASE);
        assertEquals(AnswerStatus.GIVE_UP, answerStatus);
        assertEquals(GameStatus.LOST, session.getStatus());
    }


}
