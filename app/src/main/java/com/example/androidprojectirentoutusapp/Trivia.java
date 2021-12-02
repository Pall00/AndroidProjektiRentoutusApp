package com.example.androidprojectirentoutusapp;

import androidx.annotation.NonNull;

public class Trivia {

    private int level;
    private String triviaLevel;
    private String triviaText;

    public Trivia(int level) {
        this.level = level;

        if(level==0){
            this.triviaLevel = "Level 0";
            this.triviaText = "Aloita meditointi";
        }
        else if(level==1){
            this.triviaLevel = "Level 1";
            this.triviaText = "Hienosti menee";
        }
        else if(level==2){
            this.triviaLevel = "Level 2";
            this.triviaText = "Hienoa olet jo 2 levelillä";
        }
        else if(level==3){
            this.triviaLevel = "Level 3";
            this.triviaText = "Saavutit 3 levelin";
        }
        else if(level==4){
            this.triviaLevel = "Level 4";
            this.triviaText = "Rentoudu lisää olet jo 4 levelillä";
        }
        else if(level==5){
            this.triviaLevel = "Level 5";
            this.triviaText = "5 leveli saavutettu";
        }
        else if(level==6){
            this.triviaLevel = "Level 6";
            this.triviaText = "Vielä vähän ja saavutat 6 levelin";
        }
        else if(level==7){
            this.triviaLevel = "Level 7";
            this.triviaText = "Siinä se seiska saavutettu nyt voit unohtaa meditoinnin";
        }

    }
    public String getTriviaLevel(){return triviaLevel;}

    public String getTriviaText(){return triviaText;}

    @Override
    public String toString(){return this.triviaLevel;}

}
