package com.example.androidprojectirentoutusapp;

public class Trivia {

    private int level;
    private String triviaText;

    public Trivia(int level, String text) {
        this.level = level;
        this.triviaText = text;
    }
    public String getTriviaText(){
        return triviaText;
    }
    @Override
    public String toString(){
        return "Trivia " + this.level;
    }
}