package com.example.androidprojectirentoutusapp;
/**
 * This is a class that holds trivia data which have given.
 * The values are
 * @author Juho
 * @version 1.0
 */
public class Trivia {

    private int level;
    private String triviaText;
    /**
     * This is constructor for creating new Trivia. As it is public, it can be called everywhere.
     * @param level level of trivia
     * @param text trivia text
     */
    public Trivia(int level, String text) {
        this.level = level;
        this.triviaText = text;
    }
    /**
     * This is a method for returning value of triviaText variable
     * @return user triviaText of the trivia
     */
    public String getTriviaText(){
        return triviaText;
    }
    /**
     * This is a method for returning String text of trivia and its level.
     * @return text "Trivia " and level of trivia.
     */
    @Override
    public String toString(){
        return "Trivia " + this.level;
    }
}