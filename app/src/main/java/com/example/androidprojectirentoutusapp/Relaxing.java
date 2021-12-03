package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class Relaxing extends AppCompatActivity {

    //private ImageButton imageButton;
    private CountDownTimer countDownTimer;
    private TextView textView;
    private boolean on;

    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing);
        updateBackground();
        updateRabbit();
    }

    public void imageButton(View v) {
        View view = findViewById(R.id.imageButton);
        textView = findViewById(R.id.tvTimer);
        if (!on) {
        Toast.makeText(getApplicationContext(), "Minuutti", Toast.LENGTH_SHORT).show();
            CountDownTimer timer = new CountDownTimer(5000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setText("seconds remaining: " + millisUntilFinished / 1000);

                }
                @Override
                public void onFinish() {
                    textView.setText("Valmis");
                    on = false;
                    User.getInstance().levelUp();
                    updateBackground();
                    updatePlayer();
                    updateRabbit();
                    userdataedit.commit();
                }
            }.start();
        }
        on = true;
    }

    public void updateBackground(){
        int level = User.getInstance().getLevel();
        ImageView  img = (ImageView) findViewById(R.id.imageView);
        switch(level){
            case 0:
                img.setImageResource(R.drawable.aavikkotausta);
                break;
            case 1:
                img.setImageResource(R.drawable.level1test);
                break;
            case 2:
                img.setImageResource(R.drawable.level2test);
                break;
            case 3:
                img.setImageResource(R.drawable.level3test);
                break;
            case 4:
                img.setImageResource(R.drawable.level4test);
                break;
            case 5:
                img.setImageResource(R.drawable.level5test);
                break;
            case 6:
                img.setImageResource(R.drawable.level6test);
                break;
            case 7:
                img.setImageResource(R.drawable.level7test);
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
    }
    public void updateRabbit(){

        double bmi = User.getInstance().getBmi();

        Log.i("PAINO", Integer.toString(User.getInstance().getWeight()));

        Log.i("PITUUS", Integer.toString(User.getInstance().getHeight()));

        Log.i("BMI", Double.toString(bmi));

        ImageView  imgRabbit = (ImageView) findViewById(R.id.pupuView);

        if(User.getInstance().getLevel()>2) {
            if (bmi < 15) {
                imgRabbit.setImageResource(R.drawable.himoalipaino);
            } else if (bmi < 19) {
                imgRabbit.setImageResource(R.drawable.alipaino);
            } else if (bmi < 25) {
                imgRabbit.setImageResource(R.drawable.normaali);
            } else if (bmi < 35) {
                imgRabbit.setImageResource(R.drawable.laski);
            } else {
                imgRabbit.setImageResource(R.drawable.himolaski);
            }
        }
        else{
            imgRabbit.setImageResource(0);
        }

    }
}