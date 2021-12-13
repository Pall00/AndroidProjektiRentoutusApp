package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * This is the SetupMenu class. This acts as a menu for the user that logged in. It displays all the possible parts of the program that user can go to and also
 * displays the users specifications.
 * @author Santeri Rytkönen
 * @author Juho Ahola
 * @version 1.0
 */

public class SetupMenu extends AppCompatActivity {

    private Dialog exitDialog, settingsDialog;

    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;

    private int age, height, weight;
    private String username;

    /**
     *
     * @param savedInstanceState is a bundle object that onCreate takes as a parameter. The bundle is used to save stored data of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_menu);

        updateUI();
    }

    /**
     *
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void menuButton(View v){

        if(v==findViewById(R.id.relaxButton)){
            Intent relaxIntent = new Intent(SetupMenu.this, Relaxing.class);
            startActivity(relaxIntent);
        } else if(v==findViewById(R.id.triviaButton)){
            Intent triviaIntent = new Intent(SetupMenu.this, TriviaActivity.class);
            startActivity(triviaIntent);
        }
    }

    /**
     *
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void exitButton(View v){

        exitDialog = new Dialog(this);

        exitDialog.setContentView(R.layout.exitpopup);
        ImageButton exitApp = (ImageButton) exitDialog.findViewById(R.id.exitappButton);
        ImageButton exitToLogin = (ImageButton) exitDialog.findViewById(R.id.exitlogin);
        Button closeButton = (Button) exitDialog.findViewById(R.id.closeButton);

        exitApp.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
                finishAffinity();
                System.exit(0);

            }
        });
        exitToLogin.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
                finish();
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
            }
        });

        exitDialog.show();
    }

    /**
     *
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void settingsButton(View v){

        settingsDialog = new Dialog(this);

        settingsDialog.setContentView(R.layout.settingspopup);

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        EditText eName = (EditText) settingsDialog.findViewById(R.id.textViewchangeName);
        EditText eAge = (EditText) settingsDialog.findViewById(R.id.textViewchangeAge);
        EditText eWeight = (EditText) settingsDialog.findViewById(R.id.textViewchangeWeight);
        EditText eHeight = (EditText) settingsDialog.findViewById(R.id.textViewchangeHeight);

        ImageButton changeName = (ImageButton) settingsDialog.findViewById(R.id.changenameButton);
        ImageButton changeAge = (ImageButton) settingsDialog.findViewById(R.id.changeageButton);
        ImageButton changeWeight = (ImageButton) settingsDialog.findViewById(R.id.changeweightButton);
        ImageButton changeHeight = (ImageButton) settingsDialog.findViewById(R.id.changeheightButton);
        Button exitSettings = (Button) settingsDialog.findViewById(R.id.minimizeButton);
        ImageButton resetData = (ImageButton) settingsDialog.findViewById(R.id.resetdataButton);

        changeName.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                try {
                    username = eName.getText().toString();
                    if(!(username.equals(""))){
                        User.getInstance().setName(username);
                        updatePlayer();
                        updateUI();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }

            }
        });
        changeAge.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                try{
                    age = Integer.parseInt(eAge.getText().toString());
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                if(age < 16) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                else{
                    User.getInstance().setAge(age);
                    updatePlayer();
                }
            }
        });
        changeWeight.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                try{
                    weight = Integer.parseInt(eWeight.getText().toString());
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                if(weight <= 0) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                else{
                    User.getInstance().setWeight(weight);
                    updatePlayer();
                }
            }
        });
        changeHeight.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                try{
                    height = Integer.parseInt(eHeight.getText().toString());
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                if(height<=100) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                else{
                    User.getInstance().setHeight(height);
                    updatePlayer();
                }

            }
        });
        exitSettings.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                updateUI();
                settingsDialog.dismiss();
            }
        });
        resetData.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
             */
            @Override
            public void onClick(View v) {
                User.getInstance().resetData();
                updatePlayer();
            }
        });

        settingsDialog.show();

    }

    /**
     *
     */
    public void updatePlayer(){

        int id = User.getInstance().getId();

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userdataedit = userdata.edit();

        Gson gson = new Gson();

        String json = gson.toJson(User.getInstance());

        switch(id){
            case 1:
                userdataedit.putString("User1", json);
                userdataedit.commit();
                break;
            case 2:
                userdataedit.putString("User2", json);
                userdataedit.commit();
                break;
            case 3:
                userdataedit.putString("User3", json);
                userdataedit.commit();
                break;
        }
    }

    /**
     *
     */
    public void updateUI(){
        TextView tvUsername = findViewById(R.id.textViewUsername);
        TextView tvAge = findViewById(R.id.textViewAge);
        TextView tvWeight = findViewById(R.id.textViewWeight);
        TextView tvHeight = findViewById(R.id.textViewHeight);
        TextView tvLevel = findViewById(R.id.textViewLevel);
        TextView tvRelaxing = findViewById(R.id.textViewRelaxingTime);

        int hours = User.getInstance().getRelaxingTime() / 3600;
        int minutes = (User.getInstance().getRelaxingTime() % 3600) / 60;
        int seconds = User.getInstance().getRelaxingTime() % 60;
        tvUsername.setText((User.getInstance().getName()));
        tvAge.setText("Age: " + Integer.toString(User.getInstance().getAge()));
        tvWeight.setText("Weight: " + Integer.toString(User.getInstance().getWeight()) + " kg");
        tvHeight.setText("Height: " + Integer.toString(User.getInstance().getHeight()) + " cm");
        tvRelaxing.setText("RentTime: " + String.format("%02d:%02d:%02d", hours, minutes, seconds));
        tvLevel.setText("Level: "+ Integer.toString(User.getInstance().getLevel()+1));
    }

    /**
     *
     */
    public void onResume(){
        super.onResume();
        updateUI();
    }
}