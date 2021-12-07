package com.example.androidprojectirentoutusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TriviaDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_details);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(activity_trivia.EXTRA, 0);

       ((TextView)findViewById(R.id.tvTrivia))
               .setText(GlobalTrivias.getInstance().getTrivia(i).getTriviaText());
    }
}