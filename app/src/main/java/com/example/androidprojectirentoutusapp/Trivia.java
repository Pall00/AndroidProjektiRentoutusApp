package com.example.androidprojectirentoutusapp;
/**
 * This is a class that holds trivia data which have given.
 * @author Juho
 * @version 1.0
 */
public class Trivia {

    private int level;
    private String triviaText;
    /**
     * This is constructor for creating new Trivia. As it is public, it can be called everywhere.
     * @param level the level value that corresponds with the User classes level value when applied in code properly
     * @param text the string value that holds a string of text
     */
    public Trivia(int level, String text) {
        this.level = level;
        this.triviaText = text;
    }
    /**
     * This is a method for returning the value of triviaText variable
     * @return the value of the triviaText string
     */
    public String getTriviaText(){
        return triviaText;
    }
    /**
     * This new toString method returns the String text of trivia and its level.
     * @return text "Trivia " and level of trivia.
     */
    @Override
    public String toString(){
        return "Trivia " + this.level;
    }
}