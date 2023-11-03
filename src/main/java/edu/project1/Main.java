package edu.project1;

import static edu.project1.Conf.MAX_ATTEMPTS;

public class Main {

    private Main() { }

    public static void main(String[] args) {
        AbstractDictionary dictionary = new RandomDictionary(new String[] {"qwerty", "anaconda", "lol"});
        Game game = new Game(MAX_ATTEMPTS, dictionary);
        game.run();
    }


}
