package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SetupMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_menu);

        Intent intent = getIntent();
        int user = intent.getIntExtra(MainActivity.EXTRA_USER, 0);

        Log.i("MY_APP", String.valueOf(user));
    }
}