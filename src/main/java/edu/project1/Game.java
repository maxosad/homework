package edu.project1;

import java.util.Scanner;
import static edu.project1.Conf.LOGGER;

public class Game {
    private Session session;
    private final AbstractDictionary dictionary;
    private final Integer maxAttempts;
    private static final String MORE_ZERO = "Word should have length > 0";

    public Game(Integer maxAttempts, AbstractDictionary dictionary) {
        this.maxAttempts = maxAttempts;
        this.dictionary = dictionary;
        String word =  dictionary.getWord();
        if (word.equals("")) {
            throw new IllegalArgumentException(MORE_ZERO);
        }
        session = new Session(maxAttempts, word);
    }

    private void echo(String line) {
        LOGGER.info("< " + line);
    }

    public Session getSession() {
        return session;
    }

    public void newSession() {
        String word =  dictionary.getWord();
        if (word.equals("")) {
            throw new IllegalArgumentException(MORE_ZERO);
        }
        session = new Session(maxAttempts, word);
    }

    public void run() {
        if (session.getStatus().equals(GameStatus.IN_PROGRESS)) {
            LOGGER.info("current session should be in progress");
        } else {
            LOGGER.info("Session started\n");
            String line;
            AnswerStatus answerStatus;
            try (Scanner scanner = new Scanner(System.in)) {
                while (session.getStatus().equals(GameStatus.IN_PROGRESS)) {
                    LOGGER.info("> Guess a letter:");
                    line = scanner.nextLine();
                    echo(line);
                    answerStatus = session.checkAnswer(line);
                    switch (answerStatus) {
                        case CORRECT -> LOGGER.info("> Hit!");
                        case WRONG -> LOGGER.info("> Missed, mistake %d out of %d.%n"
                            .formatted(session.getCurrentAttempt(), maxAttempts));
                        case GIVE_UP -> {
                            return;
                        }
                        default -> { }
                    }
                    String more = ">";
                    LOGGER.info(more);
                    session.printCurrentWord();
                    LOGGER.info(more);
                }
                LOGGER.info("> " + session.getStatus().getMessage());
            }
        }


    }



}
