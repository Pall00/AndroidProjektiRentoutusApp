package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "com.example.androidprojektirentoutusapp.USER";

    private int check;
    private Button user_button1;
    private Button user_button2;
    private Button user_button3;
    private SharedPreferences user1;
    private SharedPreferences user2;
    private SharedPreferences user3;
    private SharedPreferences.Editor user1edit;
    private SharedPreferences.Editor user2edit;
    private SharedPreferences.Editor user3edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user1 = getSharedPreferences("User1", MODE_PRIVATE);
        user2 = getSharedPreferences("User2", MODE_PRIVATE);
        user3 = getSharedPreferences("User3", MODE_PRIVATE);
        user_button1 = findViewById(R.id.user1_button);
        user_button2 = findViewById(R.id.user2_button);
        user_button3 = findViewById(R.id.user3_button);


        check = checkData();

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

        user1 = getSharedPreferences("User1", MODE_PRIVATE);

        if(user1.getString("Username", "0").equals("0")){
            return 1;
        }
        user2 = getSharedPreferences("User2", MODE_PRIVATE);
        if(user2.getString("Username", "0").equals("0")){
            return 2;
        }
        user3 = getSharedPreferences("User3", MODE_PRIVATE);
        if(user3.getString("Username", "0").equals("0")){
            return 3;
        }
        return 0;
    }
    public void removeButton(View v){

        user1 = getSharedPreferences("User1", MODE_PRIVATE);
        user1edit = user1.edit();

        user2 = getSharedPreferences("User2", MODE_PRIVATE);
        user2edit = user2.edit();

        user3 = getSharedPreferences("User3", MODE_PRIVATE);
        user3edit = user3.edit();

        if(v == findViewById(R.id.user1remove_button)){
            Log.i("MY_APP", "EKA USER REMOVE");
            user1edit.clear();
            user1edit.commit();
        }
        else if (v == findViewById(R.id.user2remove_button)){
            Log.i("MY_APP", "TOKA USER REMOVE");
            user2edit.clear();
            user2edit.commit();
        }
        else if(v == findViewById(R.id.user3remove_button)){
            Log.i("MY_APP", "KOLMAS USER REMOVE");
            user3edit.clear();
            user3edit.commit();
        }
        else{
            Toast.makeText(getApplicationContext(), "Nothing to remove", Toast.LENGTH_SHORT).show();
        }
        updateUI();

    }
    public void createUser(View v){

        if(checkData()!=0){
            Intent userCreateIntent = new Intent(MainActivity.this, CreateUser.class);

            userCreateIntent.putExtra(EXTRA_USER, checkData());

            startActivity(userCreateIntent);
        } else{
            Toast.makeText(getApplicationContext(), "No empty users left", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginButton(View v){

        user1 = getSharedPreferences("User1", MODE_PRIVATE);
        user2 = getSharedPreferences("User2", MODE_PRIVATE);
        user3 = getSharedPreferences("User3", MODE_PRIVATE);

        Intent loginIntent = new Intent(MainActivity.this, SetupMenu.class);

        if(v == findViewById(R.id.user1_button) && !(user1.getString("Username", "0").equals("0"))){
            User.getInstance().setValues(user1.getInt("Age", 0),user1.getInt("Weight", 0), user1.getInt("Height", 0), user1.getString("Username", "0"), user1.getInt("Level", 0), user1.getInt("Meditation_time", 0));
            startActivity(loginIntent);
        }
        else if(v== findViewById(R.id.user2_button) && !(user2.getString("Username", "0").equals("0"))){
            User.getInstance().setValues(user2.getInt("Age", 0),user2.getInt("Weight", 0), user2.getInt("Height", 0), user2.getString("Username", "0"), user2.getInt("Level", 0), user2.getInt("Meditation_time", 0));
            startActivity(loginIntent);
        }
        else if(v== findViewById(R.id.user3_button) &&!(user3.getString("Username", "0").equals("0"))){
            User.getInstance().setValues(user3.getInt("Age", 0),user3.getInt("Weight", 0), user3.getInt("Height", 0), user3.getString("Username", "0"), user3.getInt("Level", 0), user3.getInt("Meditation_time", 0));
            startActivity(loginIntent);
        }
        else{
            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
        }

    }
    public void updateUI(){
        user_button1 = findViewById(R.id.user1_button);
        user_button2 = findViewById(R.id.user2_button);
        user_button3 = findViewById(R.id.user3_button);

        user1 = getSharedPreferences("User1", MODE_PRIVATE);
        user2 = getSharedPreferences("User2", MODE_PRIVATE);
        user3 = getSharedPreferences("User3", MODE_PRIVATE);

        user_button1.setText(user1.getString("Username", "Empty user"));
        user_button2.setText(user2.getString("Username", "Empty user"));
        user_button3.setText(user3.getString("Username", "Empty user"));
    }

    protected void onResume(){
        super.onResume();
        updateUI();
    }

}