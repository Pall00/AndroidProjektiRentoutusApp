package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SetupMenu extends AppCompatActivity {

    Dialog myexitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_menu);

        TextView tv = findViewById(R.id.textViewUsername);

        Log.i("User", User.getInstance().toString());

        tv.setText((User.getInstance().getName()));

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
        TextView txtclose;

        myexitDialog = new Dialog(this);

        myexitDialog.setContentView(R.layout.exitpopup);
        exitApp = (Button) myexitDialog.findViewById(R.id.exitapp);
        exittoLogin = (Button) myexitDialog.findViewById(R.id.exitlogin);
        closeButton = (Button) myexitDialog.findViewById(R.id.closeButton);
        exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myexitDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
}