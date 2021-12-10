package com.example.androidprojectirentoutusapp;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a singleton class that holds trivia objects in a list.
 * Its purpose is to make trivia objects accessible throughout the activities. The singleton is easily accessible and modifiable.
 * With this class we are able to access the trivia objects the program gave it.
 * @author Santeri
 * @version 1.0
 */

public class GlobalTrivias {

    private List<Trivia> triviaList;
    private static final GlobalTrivias ourInstance = new GlobalTrivias();

    /**
     * This is a constructor for the GlobalTrivias class. As it is private, it can be and will be called only inside this class.
     * Creates a new Arraylist for the class and assigns it to triviaList.
     */

    private GlobalTrivias(){
        this.triviaList = new ArrayList<>();
    }

    /**
     * This is a method for returning the singleton object so that you can call its methods throughout the program.
     * @return the GlobalTrivias object created inside this class that holds all the data given to it.
     */

    public static GlobalTrivias getInstance() {
        return ourInstance;
    }

    /**
     * This is a method for returning the list that holds all the Trivias and making it accessible so that you can modify the list.
     * @return the list that holds the Trivia objects.
     */

    public List<Trivia>getTriviaList(){return triviaList;}

    /**
     * This is a method for getting the trivia in the triviaList from the given index.
     * @param i is the integer that represents the index that the user is trying to access in triviaList.
     * @return a Trivia object in the given index value.
     */

    public Trivia getTrivia(int i){ return triviaList.get(i);}
}
