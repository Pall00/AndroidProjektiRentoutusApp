package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.LineHeightSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SetupMenu extends AppCompatActivity {

    private SharedPreferences user1;
    private SharedPreferences user2;
    private SharedPreferences user3;
    private int user;

    public static final String EXTRA_USER = "com.example.androidprojektirentoutusapp.USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_menu);

        TextView tv = findViewById(R.id.textViewUsername);


        tv.setText((User.getInstance().getName()));

    }
    public void menuButton(View v){

        if(v==findViewById(R.id.relaxButton)){
            Intent relaxIntent = new Intent(SetupMenu.this, Relaxing.class);
            startActivity(relaxIntent);
        } else if(v==findViewById(R.id.triviaButton)){
            Intent triviaIntent = new Intent(SetupMenu.this, activity_trivia.class);
            startActivity(triviaIntent);
        } else if(v==findViewById(R.id.settingsButton)){
            //tähän voisi implementoida popup setting menun, pitää selvittää miten
        } else if(v==findViewById(R.id.exitButton)){
            //tähän voisi laittaa popupin jossa kysytään haluaako poistua appista vaiko takaisin kirjautumisvalikkoon
        }
    }
}