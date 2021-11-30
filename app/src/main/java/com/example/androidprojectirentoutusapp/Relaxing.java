package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Relaxing extends AppCompatActivity {

    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing);
    }

    public void imageButton(View v){
        View view = findViewById(R.id.imageButton);
        Toast.makeText(getApplicationContext(), "Hienosti meditoitu", Toast.LENGTH_SHORT).show();

    }
}