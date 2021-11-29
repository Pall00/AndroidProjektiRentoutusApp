package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
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

    private int uservalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Intent intent = getIntent();

        uservalue = intent.getIntExtra(MainActivity.EXTRA_USER, 0);

        Log.i("MY_APP", Integer.toString(uservalue));

    }

    public void Register(View v){

        SharedPreferences user1 = getSharedPreferences("User1", MODE_PRIVATE);
        SharedPreferences.Editor user1edit = user1.edit();

        SharedPreferences user2 = getSharedPreferences("User2", MODE_PRIVATE);
        SharedPreferences.Editor user2edit = user2.edit();

        SharedPreferences user3 = getSharedPreferences("User3", MODE_PRIVATE);
        SharedPreferences.Editor user3edit = user3.edit();

        eName = findViewById(R.id.etName);
        eAge = findViewById(R.id.etAge);
        eWeight = findViewById(R.id.etWeight);
        eHeight = findViewById(R.id.etHeight);

        username = eName.getText().toString();
        age = Integer.parseInt(eAge.getText().toString());
        height = Integer.parseInt(eHeight.getText().toString());
        weight = Integer.parseInt(eWeight.getText().toString());

        if(uservalue == 1){
            user1edit.putString("Username", username);
            user1edit.putInt("Age", age);
            user1edit.putInt("Weight", weight);
            user1edit.putInt("Height", height);
            user1edit.commit();
        }
        if(uservalue == 2){
            user2edit.putString("Username", username);
            user2edit.putInt("Age", age);
            user2edit.putInt("Weight", weight);
            user2edit.putInt("Height", height);
            user2edit.commit();
        }
        if(uservalue == 3){
            user3edit.putString("Username", username);
            user3edit.putInt("Age", age);
            user3edit.putInt("Weight", weight);
            user3edit.putInt("Height", height);
            user3edit.commit();
        }
        finish();

    }
}