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

        Intent intent = getIntent();

        user = intent.getIntExtra(SetupMenu.EXTRA_USER, 0);

        Log.i("MY_APP", Integer.toString(user));

        /*switch(user){
            case 1:
                age = user1.getInt("Age", 0);
                weight = user1.getInt("Weight", 0);
                height = user1.getInt("Height", 0);
                username = user1.getString("Username", "0");
                level = user1.getInt("Level", 0);
                meditation_time = user1.getInt("Meditation_time", 0);
                break;
            case 2:
                age = user2.getInt("Age", 0);
                weight = user2.getInt("Weight", 0);
                height = user2.getInt("Height", 0);
                username = user2.getString("Username", "0");
                level = user2.getInt("Level", 0);
                meditation_time = user2.getInt("Meditation_time", 0);
                break;

            case 3:
                age = user3.getInt("Age", 0);
                weight = user3.getInt("Weight", 0);
                height = user3.getInt("Height", 0);
                username = user3.getString("Username", "0");
                level = user3.getInt("Level", 0);
                meditation_time = user3.getInt("Meditation_time", 0);
                break;
        }*/


        //User u = new User(age, weight, height, username, level, meditation_time);

        //Log.i("MY_APP", "käyttäjä nyt : " + u.getName() + " ikä: " + u.getAge());

        //if(u.getLevel() == 7){
            //tee seitsemän eri juttua ja jatka tätä logiikkaa jotenkin
        //}
    }

    //tehdään singletoni acitivitystä eli tarvitaan kaksi java luokkaa activity ja activity (copy pastea vaan). Tehdään niin että seuraavalle näytölle tulustuu source filuista
    //tuleva tekstikenttä https://stackoverflow.com/questions/16821182/load-a-simple-text-file-in-android-studio ja https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
}