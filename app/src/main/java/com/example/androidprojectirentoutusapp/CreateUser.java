package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class CreateUser extends AppCompatActivity {
    private EditText eName;
    private EditText eAge;
    private EditText eWeight;
    private EditText eHeight;
    private String username;
    private int age;
    private int height;
    private  int weight;

    public User user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);




    }
}