package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Game {
    private Session session;
    private final AbstractDictionary dictionary;
    private final Integer maxAttempts;
    private static final String MORE_ZERO = "Word should have length > 0";
    private static final Logger logger = LogManager.getLogger();

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
        System.out.println("< " + line);
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
            System.out.println("current session should be in progress");
        } else {
            System.out.println("Session started\n");
            String line;
            AnswerStatus answerStatus;
            try (Scanner scanner = new Scanner(System.in)) {
                while (session.getStatus().equals(GameStatus.IN_PROGRESS)) {
                    System.out.println("> Guess a letter:");
                    line = scanner.nextLine();
                    echo(line);
                    answerStatus = session.checkAnswer(line);
                    switch (answerStatus) {
                        case CORRECT -> System.out.println("> Hit!");
                        case WRONG -> System.out.printf("> Missed, mistake %d out of %d.%n",
                            session.getCurrentAttempt(), maxAttempts
                        );
                        case GIVE_UP -> {
                            return;
                        }
                    }
                    String more = ">";
                    System.out.println(more);
                    session.printCurrentWord();
                    System.out.println(more);
                }
                System.out.println("> " + session.getStatus().getMessage());
            }
        }


    }



}
