package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Relaxing extends AppCompatActivity {

    private ImageButton imageButton;
    private CountDownTimer countDownTimer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing);
    }

    public void imageButton(View v){
        View view = findViewById(R.id.imageButton);
        textView = findViewById(R.id.tvTimer);

        Toast.makeText(getApplicationContext(), "Minuutti", Toast.LENGTH_SHORT).show();
        CountDownTimer timer = new CountDownTimer(600000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("seconds remaining: " + millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                textView.setText("Valmis");
            }
        }.start();
    }
}