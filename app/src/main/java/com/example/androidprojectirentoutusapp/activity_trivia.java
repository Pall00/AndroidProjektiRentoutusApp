package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class activity_trivia extends AppCompatActivity {

    private int user;
    private SharedPreferences user1;
    private SharedPreferences user2;
    private SharedPreferences user3;
    private SharedPreferences.Editor user1edit;
    private SharedPreferences.Editor user2edit;
    private SharedPreferences.Editor user3edit;
    private int age;
    private int weight;
    private int height;
    private String username;
    private int level;
    private int meditation_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);



        Log.i("MY_APP", User.getInstance().getName());


        //Log.i("MY_APP", "käyttäjä nyt : " + u.getName() + " ikä: " + u.getAge());

        //if(u.getLevel() == 7){
            //tee seitsemän eri juttua ja jatka tätä logiikkaa jotenkin
        //}
    }

    //tehdään singletoni acitivitystä eli tarvitaan kaksi java luokkaa activity ja activity (copy pastea vaan). Tehdään niin että seuraavalle näytölle tulustuu source filuista
    //tuleva tekstikenttä https://stackoverflow.com/questions/16821182/load-a-simple-text-file-in-android-studio ja https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
}