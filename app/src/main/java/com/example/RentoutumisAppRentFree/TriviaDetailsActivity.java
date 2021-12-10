package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TriviaDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_details);

        Bundle bundle = getIntent().getExtras();
        int i = bundle.getInt(TriviaActivity.EXTRA, 0);

       ((TextView)findViewById(R.id.tvTrivia))
               .setText(GlobalTrivias.getInstance().getTrivia(i).getTriviaText());
    }
}