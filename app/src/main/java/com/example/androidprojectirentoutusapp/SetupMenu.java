package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SetupMenu extends AppCompatActivity {

    private static final String EXTRA_USER = "com.example.androidprojektirentoutusapp.USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_menu);

        TextView tv = findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        int user = intent.getIntExtra(MainActivity.EXTRA_USER, 0);

        SharedPreferences user1 = getSharedPreferences("User1", MODE_PRIVATE);
        SharedPreferences user2 = getSharedPreferences("User2", MODE_PRIVATE);
        SharedPreferences user3 = getSharedPreferences("User3", MODE_PRIVATE);

        Log.i("MY_APP", String.valueOf(user));

        if(user == 1){
            tv.setText((user1.getString("Username", "0")));
        } else if(user == 2){
            tv.setText((user2.getString("Username", "0")));
        } else if(user ==3){
            tv.setText((user3.getString("Username", "0")));
        }
    }
    public void menuButton(View v){
        Intent intent = getIntent();

        int user = intent.getIntExtra(MainActivity.EXTRA_USER, 0);

        if(v==findViewById(R.id.relaxButton)){
            Intent relaxIntent = new Intent(SetupMenu.this, Relaxing.class);
            relaxIntent.putExtra(EXTRA_USER, user);
            startActivity(relaxIntent);
        } else if(v==findViewById(R.id.triviaButton)){
            Intent triviaIntent = new Intent(SetupMenu.this, activity_trivia.class);
            triviaIntent.putExtra(EXTRA_USER, user);
            startActivity(triviaIntent);
        } else if(v==findViewById(R.id.settingsButton)){
            //tähän voisi implementoida popup setting menun, pitää selvittää miten
        } else if(v==findViewById(R.id.exitButton)){
            //tähän voisi laittaa popupin jossa kysytään haluaako poistua appista vaiko takaisin kirjautumisvalikkoon
        }
    }
}