package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    }

    public void Register(View v){

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        eName = findViewById(R.id.textViewName);
        eAge = findViewById(R.id.textViewAge);
        eWeight = findViewById(R.id.textViewWeight);
        eHeight = findViewById(R.id.textViewHeight);

        if(valid()) {

            Gson gson = new Gson();

            Log.i("MY_APP", Integer.toString(userValue));

            if (userValue == 1) {

                User.getInstance().setValues(age, weight, height, username, 0, 0,1);
                String user = gson.toJson(User.getInstance());
                userdataedit.putString("User1", user);
            }
            else if (userValue == 2) {

                User.getInstance().setValues(age, weight, height, username, 0, 0,2);
                String user = gson.toJson(User.getInstance());
                userdataedit.putString("User2", user);
            }
            else if (userValue == 3) {

                User.getInstance().setValues(age, weight, height, username, 0, 0,3);
                String user = gson.toJson(User.getInstance());
                userdataedit.putString("User3", user);
            }
            userdataedit.commit();
            finish();
        }

    }
    public boolean valid(){
        try{
            username = eName.getText().toString();
            if(username.equals("")){
                Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                return false;
            }
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