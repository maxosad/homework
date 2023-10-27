package edu.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static edu.project1.Conf.LOGGER;

public class Session {
    public static final String GIVE_UP_PHRASE = "give up";
    private GameStatus status;
    private int currentAttempt;
    private final int maxAttempts;
    private final String wordToGuess;
    private int positionsToGuess;
    private final ArrayList<Character> currentWord;

    private final Map<Character, Boolean> usedWrongLetters;

    public Session(int maxAttempts, String wordToGuess) {
        this.maxAttempts = maxAttempts;
        currentAttempt = 0;
        this.wordToGuess = wordToGuess;
        int wordToGuessLength = wordToGuess.length();
        positionsToGuess = wordToGuessLength;
        currentWord = new ArrayList<>(wordToGuessLength);
        status = GameStatus.IN_PROGRESS;
        for (int i = 0; i < wordToGuessLength; i++) {
            currentWord.add('*');
        }
        usedWrongLetters = new HashMap<>();
    }

    public int getCurrentAttempt() {
        return currentAttempt;
    }

    public int getPositionsToGuess() {
        return positionsToGuess;
    }

    public ArrayList<Character> getCurrentWord() {
        return currentWord;
    }

    public GameStatus getStatus() {
        return status;
    }

    public AnswerStatus checkAnswer(String line) {
        if (!status.equals(GameStatus.IN_PROGRESS)) {
            return AnswerStatus.SESSION_ENDED;
        }
        AnswerStatus answerStatus;
        if (line.length() > 1) {
            answerStatus = line.equals(GIVE_UP_PHRASE) ? giveUp() : AnswerStatus.INCORRECT_INPUT;
        } else {
            Character c = line.charAt(0);
            if (usedWrongLetters.getOrDefault(c, false)) {
                return AnswerStatus.REPEATED_WRONG_LETTER;
            }

            boolean wasGuessed = false;
            for (int i = 0; i < currentWord.size(); i++) {
                if (c.equals(wordToGuess.charAt(i))) {
                    wasGuessed = true;
                    if (currentWord.get(i).equals('*')) {
                        currentWord.set(i, c);
                        positionsToGuess--;
                    }
                }
            }
            if (!wasGuessed) {
                usedWrongLetters.put(c, true);
            }
            answerStatus = wasGuessed ? correctAnswer() : wrongAnswer();
        }
        return answerStatus;
    }

    private AnswerStatus correctAnswer() {
        if (positionsToGuess == 0) {
            status = GameStatus.WIN;
        }
        return AnswerStatus.CORRECT;
    }

    private AnswerStatus wrongAnswer() {
        currentAttempt++;
        if (currentAttempt >= maxAttempts) {
            status = GameStatus.LOST;
        }
        return AnswerStatus.WRONG;
    }

    private AnswerStatus giveUp() {
        status = GameStatus.LOST;
        return AnswerStatus.GIVE_UP;
    }

    public void printCurrentWord() {
        LOGGER.info("> The word: ");
        StringBuilder sb = new StringBuilder();

        for (Character ch : currentWord) {
            sb.append(ch);
        }

        String string = sb.toString();
        LOGGER.info(string);
    }

}
