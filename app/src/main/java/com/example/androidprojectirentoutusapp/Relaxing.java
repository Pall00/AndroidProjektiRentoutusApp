package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Relaxing extends AppCompatActivity {

    //private ImageButton imageButton;
    private CountDownTimer countDownTimer;
    private TextView textView;
    private boolean on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing);
        updateUI();
    }

    public void imageButton(View v) {
        View view = findViewById(R.id.imageButton);
        textView = findViewById(R.id.tvTimer);
        if (!on) {
        Toast.makeText(getApplicationContext(), "Minuutti", Toast.LENGTH_SHORT).show();
            CountDownTimer timer = new CountDownTimer(10000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setText("seconds remaining: " + millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    textView.setText("Valmis");
                    on = false;
                    User.getInstance().levelUp();
                    updateUI();
                }
            }.start();
        }
        on = true;
    }

    public void updateUI(){
        int level = User.getInstance().getLevel();
        ImageView  img = (ImageView) findViewById(R.id.imageView);
        switch(level){
            case 0:
                img.setImageResource(R.drawable.level0test);
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
}