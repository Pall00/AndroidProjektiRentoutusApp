package com.example.androidprojectirentoutusapp;

import java.util.ArrayList;
import java.util.List;

public class GlobalTrivias {
    private List<Trivia> triviaList;
    private static final GlobalTrivias ourInstance = new GlobalTrivias();

        public static GlobalTrivias getInstance() {
            return ourInstance;
        }

        private GlobalTrivias(){
            this.triviaList = new ArrayList<>();
        }

        public List<Trivia>getTriviaList(){return triviaList;}

        public Trivia getTrivia(int i){ return triviaList.get(i);}
}
