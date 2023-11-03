package edu.project1;

public abstract class AbstractDictionary {
    protected String[] dict;

    public AbstractDictionary(String[] dict) {
        this.dict = dict;
    }

    public abstract String getWord();
}
