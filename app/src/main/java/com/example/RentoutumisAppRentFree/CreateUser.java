package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * This is the CreateUser class. In this class the user creates a new user and the program changes the data to match the user input.
 * @author Santeri Rytkönen
 * @author Juho Ahola
 * @version 1.0
 */

public class CreateUser extends AppCompatActivity {
    private EditText eName, eAge, eWeight, eHeight;
    private String username;
    private int age, height, weight,userValue;
    private final long clock = 5000;
    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;

    /**
     *This is the onCreate method. It does the default methods.
     * It gets also intent and defines userValue.
     *
     * @param savedInstanceState is a bundle object that onCreate takes as a parameter. The bundle is used to save stored data of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Intent intent = getIntent();

        userValue = intent.getIntExtra(MainActivity.EXTRA_USER,0);

    }

    /**
     *This method
     * it check if input data is valid with valid method.
     *if userValue is 1 it sets values to User1, if it is 2 values are set to user2 and if it is 3 values are set to user3.
     *it saves info using sharedpreferences and
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void Register(View v){

        eName = findViewById(R.id.textViewName);
        eAge = findViewById(R.id.textViewAge);
        eWeight = findViewById(R.id.textViewWeight);
        eHeight = findViewById(R.id.textViewHeight);

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        if(valid()) {

            Gson gson = new Gson();

            if (userValue == 1) {

                User.getInstance().setValues(username, age, weight, height, 0, 0,1, clock);
                String user = gson.toJson(User.getInstance());
                userdataedit.putString("User1", user);
            }
            else if (userValue == 2) {

                User.getInstance().setValues(username, age, weight, height, 0, 0,2, clock);
                String user = gson.toJson(User.getInstance());
                userdataedit.putString("User2", user);
            }
            else if (userValue == 3) {

                User.getInstance().setValues(username, age, weight, height, 0, 0,3, clock);
                String user = gson.toJson(User.getInstance());
                userdataedit.putString("User3", user);
            }
            userdataedit.commit();
            finish();
        }

    }

    /**
     * This method checks if values are valid
     * If username is empty method returns false
     * if age is under 16 method return false
     * if weight is 0 or under method return false
     * if height is 100 or under method return false
     * else method return true
     * if input is invalid method gives toast that says "Invalid input"
     * @return false or true depending what values are
     */
    public boolean valid(){
        try{
            username = eName.getText().toString();
            if(username.equals("")){
                Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                return false;
            }
            age = Integer.parseInt(eAge.getText().toString());
            height = Integer.parseInt(eHeight.getText().toString());
            weight = Integer.parseInt(eWeight.getText().toString());
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(height<=100 || weight <= 0 || age < 16){
            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}