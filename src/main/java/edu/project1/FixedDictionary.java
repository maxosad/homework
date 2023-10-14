package edu.project1;

public class FixedDictionary extends AbstractDictionary {


    public FixedDictionary(String[] dict) {
        super(dict);
    }

    @Override
    public String getWord() {
        return dict[0];
    }
}
