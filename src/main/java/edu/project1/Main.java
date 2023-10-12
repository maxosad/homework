package edu.project1;

public class Main {
    public static void main(String[] args) {
        AbstractDictionary dictionary = new RandomDictionary(new String[] {"qwerty", "anaconda", "lol"});
        Game game = new Game(5, dictionary);
        game.run();
    }
}
