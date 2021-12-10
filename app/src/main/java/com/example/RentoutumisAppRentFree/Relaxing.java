package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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

    private Dialog timerDialog;

    private int aika, length, minutes, clockInSeconds;

    private MediaPlayer mediaPlayer, soundPlayerNormal, soundPlayerDeath;

    private int seconds;

    private int rabbithits;

    private boolean rabbit;

    private long clock = User.getInstance().getTimer();

    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing);
        rabbit = true;
        rabbithits=0;
        timerOn = false;
        aika = 0;
        soundPlayerDeath = MediaPlayer.create(getApplicationContext(), R.raw.oofdeath);
        soundPlayerNormal = MediaPlayer.create(getApplicationContext(), R.raw.oofnormal);
        updateBackground();
        updateRabbit();
        updateButton();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.appmusic);
        Toast.makeText(getApplicationContext(), "Press the sun to begin relaxing", Toast.LENGTH_SHORT).show();
    }

    public void sunTimerButton(View v) {
        View view = findViewById(R.id.imageButtonAurinko);
        textView = findViewById(R.id.tvTimer);
        if (!timerOn) {
            mediaPlayer.start();
            CountDownTimer timer = new CountDownTimer(clock, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    clockInSeconds = (int) (millisUntilFinished / 1000);
                    minutes = clockInSeconds % 3600 / 60;
                    seconds = clockInSeconds % 60;
                    String time = String.format("%02d:%02d", minutes, seconds);
                    textView.setText("Time remaining: " + time);
                    aika = (int)  (((clock +1000)-millisUntilFinished) / 1000);
                }
                @Override
                public void onFinish() {
                    mediaPlayer.pause();
                    textView.setText("");
                    if(User.getInstance().getLevel() == 2){
                        Toast.makeText(getApplicationContext(), "You got yourself a bunny!", Toast.LENGTH_SHORT).show();
                    }
                    else if(User.getInstance().getLevel() <5 && timerOn){
                        Toast.makeText(getApplicationContext(), "You made it to the next level! New trivia available!", Toast.LENGTH_SHORT).show();
                    }
                    else if(User.getInstance().getLevel() <6 && timerOn){
                        Toast.makeText(getApplicationContext(), "Level up! Timer settings unlocked. You can now change the timer yourself. There is also a new trivia!", Toast.LENGTH_LONG).show();
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
        updateButton();
    }

    public void updateBackground(){
        int level = User.getInstance().getLevel();
        ImageView  img = findViewById(R.id.openTimerSettings);
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
    public void updateButton(){
        View button = findViewById(R.id.timerSettings);
        if(User.getInstance().getLevel() !=6){
            button.setVisibility(View.GONE);
        }
        else{
            button.setVisibility(View.VISIBLE);
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
        clock = User.getInstance().getTimer();
    }
    public void updateRabbit(){

        double bmi = User.getInstance().getBmi();

        ImageView  imgRabbit = (ImageView) findViewById(R.id.pupuView);

        if(User.getInstance().getLevel()>2) {
            if(rabbit) {
                if (bmi < 15) {
                    imgRabbit.setImageResource(R.drawable.pupuluuranko);
                } else if (bmi < 19) {
                    imgRabbit.setImageResource(R.drawable.pupuluuranko);
                } else if (bmi < 25) {
                    imgRabbit.setImageResource(R.drawable.pupu);
                } else if (bmi < 35) {
                    imgRabbit.setImageResource(R.drawable.pupupullukka);
                } else {
                    imgRabbit.setImageResource(R.drawable.pupupullukka);
                }
            }
            else{
                imgRabbit.setImageResource(R.drawable.pupuluuranko);
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
    public void timerSettings(View v){
        if(!timerOn){
            timerDialog = new Dialog(this);
            timerDialog.setContentView(R.layout.relaxtimesettings);

            Timer timer = new Timer(User.getInstance().getTimer());

            Button buttonConfirm = (Button) timerDialog.findViewById(R.id.buttonConfirmTime);
            Button buttonSecondsMinus = (Button) timerDialog.findViewById(R.id.buttonSecondsMinus);
            Button buttonSecondsPlus = (Button) timerDialog.findViewById(R.id.buttonSecondsPlus);
            Button buttonMinutesPlus = (Button) timerDialog.findViewById(R.id.buttonMinutesPlus);
            Button buttonMinutesMinus = (Button) timerDialog.findViewById(R.id.buttonMinutesMinus);
            Button buttonClose = (Button) timerDialog.findViewById(R.id.closeTimeSettingsButton);
            TextView textViewMinutes = (TextView) timerDialog.findViewById(R.id.textViewTimeMinutes);
            TextView textViewSeconds = (TextView) timerDialog.findViewById(R.id.textViewTimeSeconds);
            updateTimer(timer.getMinutes(), timer.getSeconds());
            buttonConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(timer.getMS()!=0){
                        User.getInstance().setTimer(timer.getMS());
                        updatePlayer();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Timer can't be zero!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            buttonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateTimer(timer.getMinutes(), timer.getSeconds());
                    timerDialog.dismiss();
                }
            });
            buttonMinutesPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timer.plusMinutes();
                    updateTimer(timer.getMinutes(),timer.getSeconds());
                }
            });
            buttonMinutesMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timer.minusMinutes();
                    updateTimer(timer.getMinutes(), timer.getSeconds());
                }
            });
            buttonSecondsMinus.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    timer.minusSeconds();
                    updateTimer(timer.getMinutes(), timer.getSeconds());
                }
            });
            buttonSecondsPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timer.plusSeconds();
                    updateTimer(timer.getMinutes(), timer.getSeconds());
                }
            });

            timerDialog.show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Relax first", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateTimer(int minutes, int seconds){
        TextView textViewMinutes = (TextView) timerDialog.findViewById(R.id.textViewTimeMinutes);
        TextView textViewSeconds = (TextView) timerDialog.findViewById(R.id.textViewTimeSeconds);
        textViewMinutes.setText(Integer.toString(minutes));
        textViewSeconds.setText(Integer.toString(seconds));
    }

    public void rabbitPress(View view){
        if(rabbithits==6 && rabbit){
            rabbit = false;
            soundPlayerDeath.start();
            updateRabbit();
        }
        if(User.getInstance().getLevel() > 2 && rabbit){
            Log.i("MY_APP", "KÄÄK");
            rabbithits+=1;
            soundPlayerNormal.start();
        }
    }
}