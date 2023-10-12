package edu.project1;

import org.jetbrains.annotations.NotNull;

public class FixedDictionary extends AbstractDictionary {


    public FixedDictionary(String[] dict) {
        super(dict);
    }

    @Override
    public String getWord() {
        return dict[0];
    }
}
