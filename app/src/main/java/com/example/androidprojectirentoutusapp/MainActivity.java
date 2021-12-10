package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "com.example.androidprojektirentoutusapp.USER";

    private Button user1Button;
    private Button user2Button;
    private Button user3Button;
    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;
    private String json;
    private User uTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();
        user1Button = findViewById(R.id.user1Button);
        user2Button = findViewById(R.id.user2Button);
        user3Button = findViewById(R.id.user3Button);
    }

    public int checkData(){

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

        if(v == findViewById(R.id.user1removeButton)&& !(userdata.getString("User1", "0").equals("0"))){
            userdataedit.remove("User1");
        }
        if (v == findViewById(R.id.user2removeButton) && !(userdata.getString("User2", "0").equals("0"))){
            userdataedit.remove("User2");
        }
        if(v == findViewById(R.id.user3removeButton) && !(userdata.getString("User3", "0").equals("0"))){
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

        if(v == findViewById(R.id.user1Button) && !(userdata.getString("User1", "0").equals("0"))){

            logIn("User1");
        }
        else if(v== findViewById(R.id.user2Button) && !(userdata.getString("User2", "0").equals("0"))){

            logIn("User2");
        }
        else if(v== findViewById(R.id.user3Button) && !(userdata.getString("User3", "0").equals("0"))){

            logIn("User3");
        }
        else{

            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    public void logIn(String user){

        Intent loginIntent = new Intent(MainActivity.this, SetupMenu.class);
        Gson gson = new Gson();
        json = userdata.getString(user, "0");
        uTemp = gson.fromJson(json, User.class);
        User.getInstance().setValues(uTemp.getAge(), uTemp.getWeight(), uTemp.getHeight(), uTemp.getName(), uTemp.getLevel(), uTemp.getRelaxingTime(), uTemp.getId());
        startActivity(loginIntent);
    }

    public void updateUI(){

        Gson gson = new Gson();

        if(!(userdata.getString("User1", "0").equals("0"))){
            String json = userdata.getString("User1", "0");
            User user = gson.fromJson(json, User.class);
            user1Button.setText(user.getName());
        }
        else{
            user1Button.setText("Empty user");
        }
        if(!(userdata.getString("User2", "0").equals("0"))){
            String json = userdata.getString("User2", "0");
            User user = gson.fromJson(json, User.class);
            user2Button.setText(user.getName());
        }
        else{
            user2Button.setText("Empty user");
        }
        if(!(userdata.getString("User3", "0").equals("0"))){
            String json = userdata.getString("User3", "0");
            User user = gson.fromJson(json, User.class);
            user3Button.setText(user.getName());
        }
        else{
            user3Button.setText("Empty user");
        }
    }
    protected void onResume(){
        super.onResume();
        updateUI();
    }

}