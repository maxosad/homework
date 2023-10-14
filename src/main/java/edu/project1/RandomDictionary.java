package edu.project1;

import java.util.Random;

public class RandomDictionary extends AbstractDictionary {

    private final Random random = new Random(1);

    public RandomDictionary(String[] dict) {
        super(dict);
    }

    @Override
    public String getWord() {
        return dict[random.nextInt(dict.length + 1)];
    }
}
