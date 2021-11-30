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

import com.google.gson.Gson;

public class CreateUser extends AppCompatActivity {
    private EditText eName;
    private EditText eAge;
    private EditText eWeight;
    private EditText eHeight;
    private String username;
    private int age;
    private int height;
    private  int weight;
    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;

    private int userValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Intent intent = getIntent();

        userValue = intent.getIntExtra(MainActivity.EXTRA_USER, 0);

        Log.i("MY_APP", Integer.toString(userValue));

    }

    public void Register(View v){

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

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

                userdataedit.putString("Username1", username);
                userdataedit.putInt("Age1", age);
                userdataedit.putInt("Weight1", weight);
                userdataedit.putInt("Height1", height);
                userdataedit.putInt("Level1", 0);
                userdataedit.putInt("Meditation_time1", 0);
            }
            if (userValue == 2) {


                userdataedit.putString("Username2", username);
                userdataedit.putInt("Age2", age);
                userdataedit.putInt("Weight2", weight);
                userdataedit.putInt("Height2", height);
                userdataedit.putInt("Level2", 0);
                userdataedit.putInt("Meditation_time2", 0);
            }
            if (userValue == 3) {


                userdataedit.putString("Username3", username);
                userdataedit.putInt("Age3", age);
                userdataedit.putInt("Weight3", weight);
                userdataedit.putInt("Height3", height);
                userdataedit.putInt("Level3", 0);
                userdataedit.putInt("Meditation_time3", 0);

            }
            userdataedit.commit();
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