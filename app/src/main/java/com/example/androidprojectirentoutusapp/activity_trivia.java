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

    private ArrayList<String> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        lista = new ArrayList<>();

        listView = findViewById(R.id.triviaListView);

        Log.i("MY_APP", User.getInstance().getName());


        Log.i("MY_APP", "käyttäjä nyt : " + User.getInstance().getName() + " ikä: " + User.getInstance().getAge());

        for(int i = 0; i<=User.getInstance().getLevel();i++){
            if(i==1){
                lista.add("Moi1");
            }
            if(i==2){
                lista.add("Moi2");
            }
            if(i==3){
                lista.add("Moi3");
            }
            if(i==4){
                lista.add("Moi4");
            }
            if(i==5){
                lista.add("Moi5");
            }
            if(i==6){
                lista.add("Moi6");
            }
            if(i==7){
                lista.add("Moi7");
            }
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Intent nextActivity = new Intent(MainActivity.this, PresidentDetailsActivity.class);
                //nextActivity.putExtra(EXTRA, i);
                //startActivity(nextActivity);
            }
        });
    }


    //tehdään singletoni acitivitystä eli tarvitaan kaksi java luokkaa activity ja activity (copy pastea vaan). Tehdään niin että seuraavalle näytölle tulustuu source filuista
    //tuleva tekstikenttä https://stackoverflow.com/questions/16821182/load-a-simple-text-file-in-android-studio ja https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
}