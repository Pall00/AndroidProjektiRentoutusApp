package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

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

    private int userValue;

    private SharedPreferences user1;
    private SharedPreferences user2;
    private SharedPreferences user3;
    private SharedPreferences.Editor user1edit;
    private SharedPreferences.Editor user2edit;
    private SharedPreferences.Editor user3edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Intent intent = getIntent();

        userValue = intent.getIntExtra(MainActivity.EXTRA_USER, 0);

        Log.i("MY_APP", Integer.toString(userValue));

    }

    public void Register(View v){

        user1 = getSharedPreferences("User1", MODE_PRIVATE);
        user1edit = user1.edit();

        user2 = getSharedPreferences("User2", MODE_PRIVATE);
        user2edit = user2.edit();

        user3 = getSharedPreferences("User3", MODE_PRIVATE);
        user3edit = user3.edit();

        eName = findViewById(R.id.etName);
        eAge = findViewById(R.id.etAge);
        eWeight = findViewById(R.id.etWeight);
        eHeight = findViewById(R.id.etHeight);

        if(valid()) {

            Log.i("MY_APP", username);
            Log.i("MY_APP", Integer.toString(age));
            Log.i("MY_APP", Integer.toString(height));
            Log.i("MY_APP", Integer.toString(weight));

            if (userValue == 1) {
                user1edit.putString("Username", username);
                user1edit.putInt("Age", age);
                user1edit.putInt("Weight", weight);
                user1edit.putInt("Height", height);
                user1edit.putInt("Level", 0);
                user1edit.putInt("Meditation_time", 0);
                user1edit.commit();
            }
            if (userValue == 2) {
                user2edit.putString("Username", username);
                user2edit.putInt("Age", age);
                user2edit.putInt("Weight", weight);
                user2edit.putInt("Height", height);
                user2edit.putInt("Level", 0);
                user2edit.putInt("Meditation_time", 0);
                user2edit.commit();
            }
            if (userValue == 3) {
                user3edit.putString("Username", username);
                user3edit.putInt("Age", age);
                user3edit.putInt("Weight", weight);
                user3edit.putInt("Height", height);
                user3edit.putInt("Level", 0);
                user3edit.putInt("Meditation_time", 0);
                user3edit.commit();
            }
            finish();
        }

    }
    public boolean valid(){
        try{
            username = eName.getText().toString();
            age = Integer.parseInt(eAge.getText().toString());
            height = Integer.parseInt(eHeight.getText().toString());
            weight = Integer.parseInt(eWeight.getText().toString());
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(height<=100 || weight <= 0 || age < 16){
            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}