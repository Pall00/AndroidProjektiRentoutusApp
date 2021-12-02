package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class activity_trivia extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Trivia> listAdapter;

    public static  final String EXTRA = "com.example.androidprojectirentoutusapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        listView = findViewById(R.id.triviaListView);

        Log.i("MY_APP", User.getInstance().getName());
        Log.i("MY_APP", "käyttäjä nyt : " + User.getInstance().getName() + " ikä: " + User.getInstance().getAge());

        GlobalTrivias.getInstance().getTriviaList().clear();


        for(int i = 0; i<=User.getInstance().getLevel();i++){
            if(i==1){
                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(1, "Hienosti menee"));
            }
            if(i==2){
                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(2, "Hienoa olet jo 2 levelillä"));
            }
            if(i==3){
                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(3, "Saavutit 3 levelin"));
            }
            if(i==4){
                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(4, "Rentoudu lisää olet jo 4 levelillä"));
            }
            if(i==5){
                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(5,"5 leveli saavutettu"));
            }
            if(i==6){
                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(6, "Vielä vähän ja saavutat 6 levelin"));
            }
            if(i==7){
                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(7, "Siinä se seiska saavutettu nyt voit unohtaa meditoinnin"));
            }
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, GlobalTrivias.getInstance().getTriviaList()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent nextActivity = new Intent(activity_trivia.this, TriviaDetailsActivity.class);
                nextActivity.putExtra(EXTRA, i);
                startActivity(nextActivity);
            }
        });
    }


    //tehdään singletoni acitivitystä eli tarvitaan kaksi java luokkaa activity ja activity (copy pastea vaan). Tehdään niin että seuraavalle näytölle tulustuu source filuista
    //tuleva tekstikenttä https://stackoverflow.com/questions/16821182/load-a-simple-text-file-in-android-studio ja https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
}