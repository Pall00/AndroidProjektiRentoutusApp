package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button eNewUser;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    private Button user_button1;
    private Button user_button2;
    private Button user_button3;
    private Button user1remove;
    private Button user2remove;
    private Button user3remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences user1 = getSharedPreferences("User1", MODE_PRIVATE);
        SharedPreferences user2 = getSharedPreferences("User2", MODE_PRIVATE);
        SharedPreferences user3 = getSharedPreferences("User3", MODE_PRIVATE);
        user_button1 = findViewById(R.id.user1_button);
        user_button2 = findViewById(R.id.user2_button);
        user_button3 = findViewById(R.id.user3_button);

        SharedPreferences.Editor user1edit = user1.edit();
        user1edit.putString("Username","Matti");
        user1edit.commit();
        SharedPreferences.Editor user2edit = user2.edit();
        user2edit.putString("Username","Nelli");
        user2edit.commit();


        int check = checkData();

        Log.i("MY_APP", Integer.toString(check));

        switch(check){
            case 0:
                user_button1.setText(user1.getString("Username", "0"));
                user_button2.setText(user2.getString("Username", "0"));
                user_button3.setText(user3.getString("Username", "0"));

                break;
            case 1:
                break;
            case 2:
                user_button1.setText(user1.getString("Username", "0"));
                break;
            case 3:
                user_button1.setText(user1.getString("Username", "0"));
                user_button2.setText(user2.getString("Username", "0"));
                break;
        }

    }

    public int checkData(){
        SharedPreferences user1 = getSharedPreferences("User1", MODE_PRIVATE);

        if(user1.getString("Username", "0").equals("0")){
            return 1;
        }
        SharedPreferences user2 = getSharedPreferences("User2", MODE_PRIVATE);
        if(user2.getString("Username", "0").equals("0")){
            return 2;
        }
        SharedPreferences user3 = getSharedPreferences("User3", MODE_PRIVATE);
        if(user3.getString("Username", "0").equals("0")){
            return 3;
        }
        return 0;
    }
    public void removeButton(View v){
        user1remove = findViewById(R.id.user1remove_button);
        user2remove = findViewById(R.id.user2remove_button);
        user3remove = findViewById(R.id.user3remove_button);
        SharedPreferences user1 = getSharedPreferences("User1", MODE_PRIVATE);
        SharedPreferences.Editor user1edit = user1.edit();
        SharedPreferences user2 = getSharedPreferences("User2", MODE_PRIVATE);
        SharedPreferences.Editor user2edit = user2.edit();
        SharedPreferences user3 = getSharedPreferences("User3", MODE_PRIVATE);
        SharedPreferences.Editor user3edit = user3.edit();

        if(v == findViewById(R.id.user1remove_button)){
            Log.i("MY_APP", "EKA USER REMOVE");
            user1edit.clear();
            user1edit.commit();
        }
        if(v == findViewById(R.id.user2remove_button)){
            Log.i("MY_APP", "TOKA USER REMOVE");
            user2edit.clear();
            user2edit.commit();
        }
        if(v == findViewById(R.id.user3remove_button)){
            Log.i("MY_APP", "KOLMAS USER REMOVE");
            user3edit.clear();
            user3edit.commit();
        }

    }
    public void createUser(View v){
        Intent intent = new Intent(MainActivity.this, CreateUser.class);

        startActivity(intent);
    }

}