package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class SetupMenu extends AppCompatActivity {

    private Dialog myexitDialog;

    private Dialog mysettingsDialog;

    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;

    private int age;
    private int height;
    private  int weight;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_menu);

        updateUI();

    }
    public void menuButton(View v){

        if(v==findViewById(R.id.relaxButton)){
            Intent relaxIntent = new Intent(SetupMenu.this, Relaxing.class);
            startActivity(relaxIntent);
        } else if(v==findViewById(R.id.triviaButton)){
            Intent triviaIntent = new Intent(SetupMenu.this, activity_trivia.class);
            startActivity(triviaIntent);
        }
    }
    public void exitButton(View v){

        myexitDialog = new Dialog(this);

        myexitDialog.setContentView(R.layout.exitpopup);
        Button exitApp = (Button) myexitDialog.findViewById(R.id.exitappButton);
        Button exittoLogin = (Button) myexitDialog.findViewById(R.id.exitlogin);
        Button closeButton = (Button) myexitDialog.findViewById(R.id.closeButton);

        exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myexitDialog.dismiss();
                finishAffinity();
                System.exit(0);

            }
        });
        exittoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myexitDialog.dismiss();
                finish();
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myexitDialog.dismiss();
            }
        });

        myexitDialog.show();
    }
    public void settingsButton(View v){

        mysettingsDialog = new Dialog(this);

        mysettingsDialog.setContentView(R.layout.settingspopup);

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        EditText eName = (EditText) mysettingsDialog.findViewById(R.id.textViewchangeName);
        EditText eAge = (EditText) mysettingsDialog.findViewById(R.id.textViewchangeAge);
        EditText eWeight = (EditText) mysettingsDialog.findViewById(R.id.textViewchangeWeight);
        EditText eHeight = (EditText) mysettingsDialog.findViewById(R.id.textViewchangeHeight);

        Button changeName = (Button) mysettingsDialog.findViewById(R.id.changenameButton);
        Button changeAge = (Button) mysettingsDialog.findViewById(R.id.changeageButton);
        Button changeWeight = (Button) mysettingsDialog.findViewById(R.id.changeweightButton);
        Button changeHeight = (Button) mysettingsDialog.findViewById(R.id.changeheightButton);
        Button exitSettings = (Button) mysettingsDialog.findViewById(R.id.minimizeButton);
        Button resetData = (Button) mysettingsDialog.findViewById(R.id.resetdataButton);

        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    username = eName.getText().toString();
                    if(!(username.equals(""))){
                        User.getInstance().setName(username);
                        updatePlayer();
                        updateUI();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }

            }
        });
        changeAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    age = Integer.parseInt(eAge.getText().toString());
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                if(age < 16) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                else{
                    User.getInstance().setAge(age);
                    updatePlayer();
                }
            }
        });
        changeWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    weight = Integer.parseInt(eWeight.getText().toString());
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                if(weight <= 0) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                else{
                    User.getInstance().setWeight(weight);
                    updatePlayer();
                }
            }
        });
        changeHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    height = Integer.parseInt(eHeight.getText().toString());
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                if(height<=100) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                else{
                    User.getInstance().setHeight(height);
                    updatePlayer();
                }

            }
        });
        exitSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
                mysettingsDialog.dismiss();
            }
        });
        resetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.getInstance().resetData();
                updatePlayer();
            }
        });

        mysettingsDialog.show();

    }
    public void updatePlayer(){

        int id = User.getInstance().getId();

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        Gson gson = new Gson();

        String json = gson.toJson(User.getInstance());

        switch(id){
            case 1:
                userdataedit.putString("User1", json);
                userdataedit.commit();
                break;
            case 2:
                userdataedit.putString("User2", json);
                userdataedit.commit();
                break;
            case 3:
                userdataedit.putString("User3", json);
                userdataedit.commit();
                break;
        }
    }
    public void updateUI(){
        TextView tvUsername = findViewById(R.id.textViewUsername);
        TextView tvAge = findViewById(R.id.textViewAge);
        TextView tvWeight = findViewById(R.id.textViewWeight);
        TextView tvHeight = findViewById(R.id.textViewHeight);
        TextView tvLevel = findViewById(R.id.textViewLevel);
        TextView tvMeditation = findViewById(R.id.textViewMeditationtime);

        tvUsername.setText((User.getInstance().getName()));
        tvAge.setText("Age: " + Integer.toString(User.getInstance().getAge()));
        tvWeight.setText("Weight: " + Integer.toString(User.getInstance().getWeight()));
        tvHeight.setText("Height: " + Integer.toString(User.getInstance().getHeight()));
        tvLevel.setText("Level: " + Integer.toString(User.getInstance().getLevel()));
        tvMeditation.setText("Meditated time: " + Integer.toString(User.getInstance().getMeditationTime()));
    }
    public void onResume(){
        super.onResume();
        updateUI();
    }
}