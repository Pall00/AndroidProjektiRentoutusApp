package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * This is the TriviaDetailsActivity class. Its only job is to display the Trivia from the GlobalTrivia singleton object with the integer value that represents the
 * index value of the wanted Trivia to be displayed.
 * @author Santeri Rytkönen
 * @author Juho Ahola
 * @version 1.0
 */

public class TriviaDetailsActivity extends AppCompatActivity {
    /**
     *
     * @param savedInstanceState
     */
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