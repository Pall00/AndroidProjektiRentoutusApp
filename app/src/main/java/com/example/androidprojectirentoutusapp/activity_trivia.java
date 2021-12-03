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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


                InputStream file = getResources().openRawResource(R.raw.trivia1);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(1, result));
            }
            if(i==2){

                InputStream file = getResources().openRawResource(R.raw.trivia2);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(2, result));
            }
            if(i==3){

                InputStream file = getResources().openRawResource(R.raw.trivia3);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(3, result));
            }
            if(i==4){
                InputStream file = getResources().openRawResource(R.raw.trivia4);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(4, result));
            }
            if(i==5){
                InputStream file = getResources().openRawResource(R.raw.trivia5);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(5,result));
            }
            if(i==6){
                InputStream file = getResources().openRawResource(R.raw.trivia6);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(6, result));
            }
            if(i==7){
                InputStream file = getResources().openRawResource(R.raw.trivia7);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivias.getInstance().getTriviaList().add(new Trivia(7, result));
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