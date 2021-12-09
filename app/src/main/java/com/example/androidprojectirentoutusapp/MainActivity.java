package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
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

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.taustamusa);
        //mediaPlayer.start();


        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        user_button1 = findViewById(R.id.user1Button);
        user_button2 = findViewById(R.id.user2Button);
        user_button3 = findViewById(R.id.user3Button);

        updateUI();
    }

    public int checkData(){
        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);

        if(userdata.getString("User1", "0").equals("0")){
            return 1;
        }
        if(userdata.getString("User2", "0").equals("0")){
            return 2;
        }
        if(userdata.getString("User3", "0").equals("0")){
            return 3;
        }
        return 0;
    }

    public void removeButton(View v){
        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        if(v == findViewById(R.id.user1removeButton)&& !(userdata.getString("User1", "0").equals("0"))){
            userdataedit.remove("User1");
        }
        else if (v == findViewById(R.id.user2removeButton) && !(userdata.getString("User2", "0").equals("0"))){
            userdataedit.remove("User2");
        }
        else if(v == findViewById(R.id.user3removeButton) && !(userdata.getString("User3", "0").equals("0"))){
            userdataedit.remove("User3");
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

        Gson gson = new Gson();

        Intent loginIntent = new Intent(MainActivity.this, SetupMenu.class);

        if(v == findViewById(R.id.user1Button) && !(userdata.getString("User1", "0").equals("0"))){

            String json = userdata.getString("User1", "0");
            User user = gson.fromJson(json, User.class);

            User.getInstance().setValues(user.getAge(), user.getWeight(), user.getHeight(), user.getName(), user.getLevel(), user.getRelaxingTime(), user.getId());

            startActivity(loginIntent);
        }
        else if(v== findViewById(R.id.user2Button) && !(userdata.getString("User2", "0").equals("0"))){

            String json = userdata.getString("User2", "0");
            User user = gson.fromJson(json, User.class);

            User.getInstance().setValues(user.getAge(), user.getWeight(), user.getHeight(), user.getName(), user.getLevel(), user.getRelaxingTime(), user.getId());

            startActivity(loginIntent);
        }
        else if(v== findViewById(R.id.user3Button) && !(userdata.getString("User3", "0").equals("0"))){

            String json = userdata.getString("User3", "0");
            User user = gson.fromJson(json, User.class);

            User.getInstance().setValues(user.getAge(), user.getWeight(), user.getHeight(), user.getName(), user.getLevel(), user.getRelaxingTime(), user.getId());

            startActivity(loginIntent);
        }
        else{
            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
        }

    }
    public void updateUI(){
        check = checkData();

        if(check!=1) {
            userdata = getSharedPreferences("Userdata", MODE_PRIVATE);

            Gson gson = new Gson();

            if(check==2){
                String json1 = userdata.getString("User1", "0");
                User user1 = gson.fromJson(json1, User.class);

                user_button1.setText(user1.getName());
                user_button2.setText("Empty user");
                user_button3.setText("Empty user");
            }
            if(check==3){
                String json1 = userdata.getString("User1", "0");
                User user1 = gson.fromJson(json1, User.class);

                String json2 = userdata.getString("User2", "0");
                User user2 = gson.fromJson(json2, User.class);

                user_button1.setText(user1.getName());
                user_button2.setText(user2.getName());
                user_button3.setText("Empty user");
            }
            if(check==0){
                String json1 = userdata.getString("User1", "0");
                User user1 = gson.fromJson(json1, User.class);

                String json2 = userdata.getString("User2", "0");
                User user2 = gson.fromJson(json2, User.class);

                String json3 = userdata.getString("User3", "0");
                User user3 = gson.fromJson(json3, User.class);

                user_button1.setText(user1.getName());
                user_button2.setText(user2.getName());
                user_button3.setText(user3.getName());
            }
        }
        else{
            user_button1.setText("Empty user");
        }
    }
    protected void onResume(){
        super.onResume();
        updateUI();
    }

}