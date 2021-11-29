package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class CreateUser extends AppCompatActivity {
    private EditText eName;
    private EditText eAge;
    private EditText eWeight;
    private EditText eHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        eName = findViewById(R.id.etName);
        eAge = findViewById(R.id.etAge);
        eHeight = findViewById(R.id.etHeight);
        eWeight = findViewById(R.id.etWeight);






    }
}