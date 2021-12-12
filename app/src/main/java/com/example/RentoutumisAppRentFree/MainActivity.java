package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "com.example.androidprojektirentoutusapp.USER";
    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;
    private String json;
    private User uTemp;
    private TextView user1tv,user2tv,user3tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

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
        User.getInstance().setValues(uTemp.getName(), uTemp.getAge(), uTemp.getWeight(), uTemp.getHeight(), uTemp.getLevel(), uTemp.getRelaxingTime(), uTemp.getId(), uTemp.getTimer());
        startActivity(loginIntent);
    }

    public void updateUI(){

        Gson gson = new Gson();

        user1tv = findViewById(R.id.user1textView);
        user2tv = findViewById(R.id.user2textView);
        user3tv = findViewById(R.id.user3textView);

        if(!(userdata.getString("User1", "0").equals("0"))){
            String json = userdata.getString("User1", "0");
            User user = gson.fromJson(json, User.class);
            user1tv.setText(user.getName());
        }
        else{
            user1tv.setText("Empty user");
        }
        if(!(userdata.getString("User2", "0").equals("0"))){
            String json = userdata.getString("User2", "0");
            User user = gson.fromJson(json, User.class);
            user2tv.setText(user.getName());
        }
        else{
            user2tv.setText("Empty user");
        }
        if(!(userdata.getString("User3", "0").equals("0"))){
            String json = userdata.getString("User3", "0");
            User user = gson.fromJson(json, User.class);
            user3tv.setText(user.getName());
        }
        else{
            user3tv.setText("Empty user");
        }
    }
    protected void onResume(){
        super.onResume();
        updateUI();
    }

}