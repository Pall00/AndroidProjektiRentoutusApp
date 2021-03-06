package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.InputStream;
import java.util.Scanner;

/**
 * This is the TriviaActivity class. Its job is to display the given Trivia data to listview widget and setup the correct message to be sent to the TriviaDetailsActivty
 * class.
 * @author Santeri Rytkönen
 * @author Juho Ahola
 * @version 1.0
 */

public class TriviaActivity extends AppCompatActivity {

    private ListView listView;

    public static  final String EXTRA = "com.example.RentoutumisAppRentFree.MESSAGE";

    /**
     *This is the onCreate method. It does the default methods it has. It also checks users level and reads trivial texts from txt files and shows trivial levels on listview depending on users level.
     *@param savedInstanceState is a bundle object that onCreate takes as a parameter. The bundle is used to save stored data of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        listView = findViewById(R.id.triviaListView);

        GlobalTrivia.getInstance().getTriviaList().clear();


        for(int i = 0; i<=(User.getInstance().getLevel()+1);i++){
            if(i==1){

                InputStream file = getResources().openRawResource(R.raw.trivia1);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivia.getInstance().getTriviaList().add(new Trivia(1, result));
            }
            if(i==2){

                InputStream file = getResources().openRawResource(R.raw.trivia2);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivia.getInstance().getTriviaList().add(new Trivia(2, result));
            }
            if(i==3){

                InputStream file = getResources().openRawResource(R.raw.trivia3);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivia.getInstance().getTriviaList().add(new Trivia(3, result));
            }
            if(i==4){
                InputStream file = getResources().openRawResource(R.raw.trivia4);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivia.getInstance().getTriviaList().add(new Trivia(4, result));
            }
            if(i==5){
                InputStream file = getResources().openRawResource(R.raw.trivia5);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivia.getInstance().getTriviaList().add(new Trivia(5,result));
            }
            if(i==6){
                InputStream file = getResources().openRawResource(R.raw.trivia6);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivia.getInstance().getTriviaList().add(new Trivia(6, result));
            }
            if(i==7){
                InputStream file = getResources().openRawResource(R.raw.trivia7);

                Scanner s = new Scanner(file).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                GlobalTrivia.getInstance().getTriviaList().add(new Trivia(7, result));
            }
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, GlobalTrivia.getInstance().getTriviaList()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             *This is method starts new activity onclick that shows clicked trivial text on new view.
             * @param parent the AdapterView object that was clicked
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             * @param i is the index of the element that was clicked from the listview
             * @param id the ID of the item that was clicked
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int i, long id) {
                Intent nextActivity = new Intent(TriviaActivity.this, TriviaDetailsActivity.class);
                nextActivity.putExtra(EXTRA, i);
                startActivity(nextActivity);
            }
        });
    }
}