package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the MainActivity class. It acts as a login and a signup activty where the user can create, delete or make users.
 * @author Santeri Rytkönen
 * @author Juho Ahola
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "com.example.androidprojektirentoutusapp.USER";
    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;
    private String json;
    private User uTemp;
    private TextView user1tv,user2tv,user3tv;

    /**
     * This is the onCreate method. It does the default methods it has and also defines userdata and userdataedit values
     * @param savedInstanceState is a bundle object that onCreate takes as a parameter. The bundle is used to save stored data of the activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();
    }

    /**
     * This is a method to check from 1 to 3 the first userdata that is missing. If the first userdata is missing it returns 1, if it isn't missing it checks the second
     * and does the same operation and returns 2 if it doesnt exist. Same thing for 3 but the return value is now 3. If all three users exist in userdata it returns 0,
     * indicating that every available Users are taken
     * @return integer that represents User and its order number
     */

    public int checkData(){

        if(userdata.getString("User1", "0").equals("0")){
            return 1;
        }
        if(userdata.getString("User2", "0").equals("0")){
            return 2;
        }
        if(userdata.getString("User3", "0").equals("0")){
            return 3;
        }
        return 0;
    }

    /**
     * This is a method that gets called whenever user presses the remove buttons next to users. It checks which of the three buttons was pressed, checks if there is
     * data for that specific user and if there is, it deletes it. If userdata doesn't exist, it makes a Toast message that gives the user a message saying that there
     * was no userdata to remove. After that it commits the changes to the data and calls updateUI method.
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure out which button was pressed
     */

    public void removeButton(View v){

        if(v == findViewById(R.id.user1removeButton)&& !(userdata.getString("User1", "0").equals("0"))){
            userdataedit.remove("User1");
        }
        else if (v == findViewById(R.id.user2removeButton) && !(userdata.getString("User2", "0").equals("0"))){
            userdataedit.remove("User2");
        }
        else if(v == findViewById(R.id.user3removeButton) && !(userdata.getString("User3", "0").equals("0"))){
            userdataedit.remove("User3");
        }
        else{
            Toast.makeText(getApplicationContext(), "Nothing to remove", Toast.LENGTH_SHORT).show();
        }
        userdataedit.commit();

        updateUI();
    }

    /**
     * This method gets called when the user presses the buttonCreateUser button. It then checks if the return value from checkData method is anything else than zero,
     * indicating that there is available space for creating an ew user. It then puts the return value of checkData to Intent as an extra and starts the new Activity
     * with the userCreateIntent Intent object. If there are no space left for new users, it creates a Toast message to user indicating that no user exist.
     * The intent that gets made is to CreateUSer class.
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure out which button was pressed
     */

    public void createUser(View v){
        if(checkData()!=0){
            Intent userCreateIntent = new Intent(MainActivity.this, CreateUser.class);

            userCreateIntent.putExtra(EXTRA_USER, checkData());

            startActivity(userCreateIntent);
        } else{
            Toast.makeText(getApplicationContext(), "No empty users left", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method gets called when the user presses anyone the user buttons to login to that specific user. The method checks which of the buttons were pressed and
     * then it checks if data exists for that user. If it does, it called logIn() method with the corresponding data key for that user.
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure out which button was pressed
     */

    public void loginButton(View v){

        if(v == findViewById(R.id.user1Button) && !(userdata.getString("User1", "0").equals("0"))){

            logIn("User1");
        }
        else if(v== findViewById(R.id.user2Button) && !(userdata.getString("User2", "0").equals("0"))){

            logIn("User2");
        }
        else if(v== findViewById(R.id.user3Button) && !(userdata.getString("User3", "0").equals("0"))){

            logIn("User3");
        }
        else{

            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This methods creates a Intent for the SetUpMenu class. It also makes a JSON string conversion from the userdata and makes a new User object with it. It then
     * puts those values taken from the data to the User singleton setValues() parameters with its getters, initializing the values for the singleton that gets used
     * throughout the program.
     * @param user is a string that represents the key that exists in SharedPreferences data. It uses it as a parameter on userdata.getString() method.
     */

    public void logIn(String user){

        Intent loginIntent = new Intent(MainActivity.this, SetupMenu.class);
        Gson gson = new Gson();
        json = userdata.getString(user, "0");
        uTemp = gson.fromJson(json, User.class);
        User.getInstance().setValues(uTemp.getName(), uTemp.getAge(), uTemp.getWeight(), uTemp.getHeight(), uTemp.getLevel(), uTemp.getRelaxingTime(), uTemp.getId(), uTemp.getTimer());
        startActivity(loginIntent);
    }

    /**
     * This method updates the UI. It checks for every user TextView if the userdata that it represents exists. If it doesn't it puts "Empty user" text to it and if it
     * does it creates a new User object and calls it getName() method to sets the TextViews text equal to userdatas name value.
     */

    public void updateUI(){

        Gson gson = new Gson();

        user1tv = findViewById(R.id.user1textView);
        user2tv = findViewById(R.id.user2textView);
        user3tv = findViewById(R.id.user3textView);

        if(!(userdata.getString("User1", "0").equals("0"))){
            String json = userdata.getString("User1", "0");
            User user = gson.fromJson(json, User.class);
            user1tv.setText(user.getName());
        }
        else{
            user1tv.setText("Empty user");
        }
        if(!(userdata.getString("User2", "0").equals("0"))){
            String json = userdata.getString("User2", "0");
            User user = gson.fromJson(json, User.class);
            user2tv.setText(user.getName());
        }
        else{
            user2tv.setText("Empty user");
        }
        if(!(userdata.getString("User3", "0").equals("0"))){
            String json = userdata.getString("User3", "0");
            User user = gson.fromJson(json, User.class);
            user3tv.setText(user.getName());
        }
        else{
            user3tv.setText("Empty user");
        }
    }

    /**
     * This is the standard onResume() method from Android Lifecycle but on every onResume() it updates the UI. With that whenever user returns from the CreateUser
     * activity, the program knows to update the UI to match the changed data.
     */

    protected void onResume(){
        super.onResume();
        updateUI();
    }

}