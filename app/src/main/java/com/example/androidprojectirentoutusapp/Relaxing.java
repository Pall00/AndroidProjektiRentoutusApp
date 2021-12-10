package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class Relaxing extends AppCompatActivity {

    private TextView textView;
    private boolean timerOn;
    private Dialog exitDialog;

    private int aika;

    private MediaPlayer mediaPlayer;

    private int length;

    private final long clock = 60000;

    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing);
        timerOn = false;
        aika = 0;
        updateBackground();
        updateRabbit();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.appmusic);
        Toast.makeText(getApplicationContext(), "Press the sun to begin relaxing", Toast.LENGTH_SHORT).show();
    }

    public void sunTimerButton(View v) {
        View view = findViewById(R.id.imageButtonAurinko);
        textView = findViewById(R.id.tvTimer);
        if (!timerOn) {
            mediaPlayer.start();
        //Toast.makeText(getApplicationContext(), "Minuutti", Toast.LENGTH_SHORT).show();
            CountDownTimer timer = new CountDownTimer(clock, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setText("Time remaining: " + millisUntilFinished / 1000);
                    aika = (int)  (((clock +1000)-millisUntilFinished) / 1000);
                }
                @Override
                public void onFinish() {
                    mediaPlayer.pause();
                    textView.setText("");
                    if(User.getInstance().getLevel() == 2){
                        Toast.makeText(getApplicationContext(), "You got yourself a bunny!", Toast.LENGTH_SHORT).show();
                    }
                    else if(User.getInstance().getLevel() <6 && timerOn){
                        Toast.makeText(getApplicationContext(), "You made it to the next level! New trivia available!", Toast.LENGTH_SHORT).show();
                    }
                    else if (timerOn){
                        Toast.makeText(getApplicationContext(), "You finished your session! Keep up the good work!", Toast.LENGTH_SHORT).show();
                    }
                    if (timerOn) {
                        User.getInstance().levelUp();
                        User.getInstance().addRelaxingTime(aika);
                    }
                    timerOn = false;
                    aika = 0;
                    updateAll();

                }
            }.start();
        }
        else{
            Toast.makeText(getApplicationContext(), "Timer is already on! Relax!", Toast.LENGTH_SHORT).show();
        }
        timerOn = true;
    }

    public void updateAll(){
        updateBackground();
        updatePlayer();
        updateRabbit();
    }

    public void updateBackground(){
        int level = User.getInstance().getLevel();
        ImageView  img = findViewById(R.id.imageView);
        switch(level){
            case 0:
                img.setImageResource(R.drawable.level1);
                break;
            case 1:
                img.setImageResource(R.drawable.level2);
                break;
            case 2:
                img.setImageResource(R.drawable.level3);
                break;
            case 3:
                img.setImageResource(R.drawable.level4);
                break;
            case 4:
                img.setImageResource(R.drawable.level5);
                break;
            case 5:
                img.setImageResource(R.drawable.level6);
                break;
            case 6:
                img.setImageResource(R.drawable.level7);
                break;
        }
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
                break;
            case 2:
                userdataedit.putString("User2", json);
                break;
            case 3:
                userdataedit.putString("User3", json);
                break;
        }
        userdataedit.commit();
    }
    public void updateRabbit(){

        double bmi = User.getInstance().getBmi();

        ImageView  imgRabbit = (ImageView) findViewById(R.id.pupuView);

        if(User.getInstance().getLevel()>2) {
            if (bmi < 15) {
                imgRabbit.setImageResource(R.drawable.pupu);
            } else if (bmi < 19) {
                imgRabbit.setImageResource(R.drawable.pupu);
            } else if (bmi < 25) {
                imgRabbit.setImageResource(R.drawable.pupu);
            } else if (bmi < 35) {
                imgRabbit.setImageResource(R.drawable.pupu);
            } else {
                imgRabbit.setImageResource(R.drawable.pupu);
            }
        }
        else{
            imgRabbit.setImageResource(0);
        }
    }

    public void onBackPressed(){
        if(!timerOn){
            super.onBackPressed();
        }
        else{
            exitDialog = new Dialog(this);

            exitDialog.setContentView(R.layout.makingsurepopup);
            Button yesButton = (Button) exitDialog.findViewById(R.id.yesButton);
            Button noButton = (Button) exitDialog.findViewById(R.id.noButton);
            Button closeButton = (Button) exitDialog.findViewById(R.id.closeButton);
            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitDialog.dismiss();
                    User.getInstance().addRelaxingTime(aika);
                    updatePlayer();
                    timerOn = false;
                    mediaPlayer.stop();
                    finish();
                }
            });
            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitDialog.dismiss();
                }
            });
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitDialog.dismiss();
                }
            });

            exitDialog.show();
        }
    }
    protected void onPause(){
        super.onPause();
        length= mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
    }
    protected void onResume(){
        super.onResume();
        if(timerOn){
            mediaPlayer.start();
            mediaPlayer.seekTo(length);
        }
    }
}