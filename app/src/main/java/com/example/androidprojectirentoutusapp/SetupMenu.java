package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        } else if(v==findViewById(R.id.settingsButton)){
            //tähän voisi implementoida popup setting menun, pitää selvittää miten
        } else if(v==findViewById(R.id.exitButton)){
            //tähän voisi laittaa popupin jossa kysytään haluaako poistua appista vaiko takaisin kirjautumisvalikkoon
        }
    }
    public void exitButton(View v){
        Button exitApp;
        Button exittoLogin;
        Button closeButton;

        myexitDialog = new Dialog(this);

        myexitDialog.setContentView(R.layout.exitpopup);
        exitApp = (Button) myexitDialog.findViewById(R.id.exitapp);
        exittoLogin = (Button) myexitDialog.findViewById(R.id.exitlogin);
        closeButton = (Button) myexitDialog.findViewById(R.id.closeButton);
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

        Button changeName;
        Button changeAge;
        Button changeWeight;
        Button changeHeight;
        Button exitSettings;
        Button resetData;

        EditText eName;
        EditText eAge;
        EditText eWeight;
        EditText eHeight;

        eName = (EditText) mysettingsDialog.findViewById(R.id.textViewchangeName);
        eAge = (EditText) mysettingsDialog.findViewById(R.id.textViewchangeAge);
        eWeight = (EditText) mysettingsDialog.findViewById(R.id.textViewchangeWeight);
        eHeight = (EditText) mysettingsDialog.findViewById(R.id.textViewchangeHeight);


        changeName = (Button) mysettingsDialog.findViewById(R.id.changenameButton);
        changeAge = (Button) mysettingsDialog.findViewById(R.id.changeageButton);
        changeWeight = (Button) mysettingsDialog.findViewById(R.id.changeweightButton);
        changeHeight = (Button) mysettingsDialog.findViewById(R.id.changeheightButton);
        exitSettings = (Button) mysettingsDialog.findViewById(R.id.minimizeButton);
        resetData = (Button) mysettingsDialog.findViewById(R.id.resetdataButton);

        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    username = eName.getText().toString();
                    User.getInstance().setName(username);
                    updatePlayer();
                    updateUI();
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

        //ope neuvoi tekemään tästä kaikesta datasta GSON jne jne juttua

        int id = User.getInstance().getId();

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        switch(id){
            case 1:
                userdataedit.putString("Username1", User.getInstance().getName());
                userdataedit.putInt("Meditationtime1", User.getInstance().getMeditationTime());
                userdataedit.putInt("Weight1", User.getInstance().getWeight());
                userdataedit.putInt("Height1", User.getInstance().getHeight());
                userdataedit.putInt("Level1", User.getInstance().getLevel());
                userdataedit.putInt("Age1", User.getInstance().getAge());
                userdataedit.commit();
                break;
            case 2:
                userdataedit.putString("Username2", User.getInstance().getName());
                userdataedit.putInt("Meditationtime2", User.getInstance().getMeditationTime());
                userdataedit.putInt("Weight2", User.getInstance().getWeight());
                userdataedit.putInt("Height2", User.getInstance().getHeight());
                userdataedit.putInt("Level2", User.getInstance().getLevel());
                userdataedit.putInt("Age2", User.getInstance().getAge());
                userdataedit.commit();
                break;
            case 3:
                userdataedit.putString("Username3", User.getInstance().getName());
                userdataedit.putInt("Meditationtime3", User.getInstance().getMeditationTime());
                userdataedit.putInt("Weight3", User.getInstance().getWeight());
                userdataedit.putInt("Height3", User.getInstance().getHeight());
                userdataedit.putInt("Level3", User.getInstance().getLevel());
                userdataedit.putInt("Age3", User.getInstance().getAge());
                userdataedit.commit();
                break;
        }
    }
    public void updateUI(){
        TextView tv = findViewById(R.id.textViewUsername);

        tv.setText((User.getInstance().getName()));
    }
}