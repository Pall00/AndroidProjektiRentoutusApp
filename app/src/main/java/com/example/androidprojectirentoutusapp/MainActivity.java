package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
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
    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        user_button1 = findViewById(R.id.user1_button);
        user_button2 = findViewById(R.id.user2_button);
        user_button3 = findViewById(R.id.user3_button);


        check = checkData();

        Log.i("MY_APP", Integer.toString(check));

        switch(check){
            case 0:
                user_button1.setText(userdata.getString("Username1", "0"));
                user_button2.setText(userdata.getString("Username2", "0"));
                user_button3.setText(userdata.getString("Username3", "0"));
                break;
            case 1:
                break;
            case 2:
                user_button1.setText(userdata.getString("Username1", "0"));
                break;
            case 3:
                user_button1.setText(userdata.getString("Username1", "0"));
                user_button2.setText(userdata.getString("Username2", "0"));
                break;
        }

    }

    public int checkData(){

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);

        if(userdata.getString("Username1", "0").equals("0")){
            return 1;
        }
        if(userdata.getString("Username2", "0").equals("0")){
            return 2;
        }
        if(userdata.getString("Username3", "0").equals("0")){
            return 3;
        }
        return 0;
    }

    public void removeButton(View v){

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        if(v == findViewById(R.id.user1remove_button)){
            Log.i("MY_APP", "EKA USER REMOVE");
            userdataedit.remove("Username1");
        }
        else if (v == findViewById(R.id.user2remove_button)){
            Log.i("MY_APP", "TOKA USER REMOVE");
            userdataedit.remove("Username2");
        }
        else if(v == findViewById(R.id.user3remove_button)){
            Log.i("MY_APP", "KOLMAS USER REMOVE");
            userdataedit.remove("Username3");
        }
        else{
            Toast.makeText(getApplicationContext(), "Nothing to remove", Toast.LENGTH_SHORT).show();
        }

        userdataedit.commit();

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

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);

        Intent loginIntent = new Intent(MainActivity.this, SetupMenu.class);

        if(v == findViewById(R.id.user1_button) && !(userdata.getString("Username1", "0").equals("0"))){
            User.getInstance().setValues(userdata.getInt("Age1", 0),userdata.getInt("Weight1", 0), userdata.getInt("Height1", 0), userdata.getString("Username1", "0"), userdata.getInt("Level1", 0), userdata.getInt("Meditation_time1", 0), 1);
            startActivity(loginIntent);
        }
        else if(v== findViewById(R.id.user2_button) && !(userdata.getString("Username2", "0").equals("0"))){
            User.getInstance().setValues(userdata.getInt("Age2", 0),userdata.getInt("Weight2", 0), userdata.getInt("Height2", 0), userdata.getString("Username2", "0"), userdata.getInt("Level2", 0), userdata.getInt("Meditation_time2", 0), 2);
            startActivity(loginIntent);
        }
        else if(v== findViewById(R.id.user3_button) && !(userdata.getString("Username3", "0").equals("0"))){
            User.getInstance().setValues(userdata.getInt("Age3", 0),userdata.getInt("Weight3", 0), userdata.getInt("Height3", 0), userdata.getString("Username3", "0"), userdata.getInt("Level3", 0), userdata.getInt("Meditation_tim3", 0), 3);
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

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        user_button1.setText(userdata.getString("Username1", "Empty user"));
        user_button2.setText(userdata.getString("Username2", "Empty user"));
        user_button3.setText(userdata.getString("Username3", "Empty user"));
    }

    protected void onResume(){
        super.onResume();
        updateUI();
    }

}