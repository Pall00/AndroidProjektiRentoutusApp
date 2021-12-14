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
    private SharedPreferences.Editor userDataEdit;
    private int age, height, weight;
    private String username;

    /**
     * This is the onCreate() method. It does the default methods and also calls updateUI() method.
     * @param savedInstanceState is a bundle object that onCreate takes as a parameter. The bundle is used to save stored data of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_menu);

        updateUI();
    }

    /**
     * This method gets called when the user presses either the relax button or trivia button. The method checks which button was pressed. If the button was relax button,
     * the method sets up an Intent for Relaxing class and starts it. If the button was trivia button then it does the same for TriviaActivity class.
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
     * This method gets called when the user presses the exit button. It initializes an exitDialog and sets up the buttons for it and starts it up.
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void exitButton(View v){

        exitDialog = new Dialog(this);

        exitDialog.setContentView(R.layout.exitpopup);
        ImageButton exitApp = (ImageButton) exitDialog.findViewById(R.id.exitAppButton);
        ImageButton exitToLogin = (ImageButton) exitDialog.findViewById(R.id.exitLoginButton);
        Button closeButton = (Button) exitDialog.findViewById(R.id.closeButton);

        exitApp.setOnClickListener(new View.OnClickListener() {
            /**
             * This method gets called when the user presses the exitApp button. This method closes the Dialog, closes the app and shuts it down entirely.
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
             * This method gets called when the user presses the exitToLogin button. This method closes the Dialog and exists the current activity.
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
             * This method gets called when the user presses the closeButton button. This closes the current Dialog.
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
     * This method gets called when the user presses the settings button. It initializes a new settingsDialog Dialog and creates the its buttons and EditText objects.
     * It also defines userData and userDataEdit values.
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void settingsButton(View v){

        settingsDialog = new Dialog(this);

        settingsDialog.setContentView(R.layout.settingspopup);

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userDataEdit = userdata.edit();

        EditText eName = (EditText) settingsDialog.findViewById(R.id.textViewChangeName);
        EditText eAge = (EditText) settingsDialog.findViewById(R.id.textViewChangeAge);
        EditText eWeight = (EditText) settingsDialog.findViewById(R.id.textViewChangeWeight);
        EditText eHeight = (EditText) settingsDialog.findViewById(R.id.textViewChangeHeight);

        ImageButton changeName = (ImageButton) settingsDialog.findViewById(R.id.changeNameButton);
        ImageButton changeAge = (ImageButton) settingsDialog.findViewById(R.id.changeAgeButton);
        ImageButton changeWeight = (ImageButton) settingsDialog.findViewById(R.id.changeWeightButton);
        ImageButton changeHeight = (ImageButton) settingsDialog.findViewById(R.id.changeHeightButton);
        Button exitSettings = (Button) settingsDialog.findViewById(R.id.minimizeButton);
        ImageButton resetData = (ImageButton) settingsDialog.findViewById(R.id.resetDataButton);

        changeName.setOnClickListener(new View.OnClickListener() {
            /**
             * This method gets called when the user presses the changeName button. It tries to set the username variable to the eName value. As the username variable
             * is string, if the eName cannot be converted to it it Toasts invalid input message to the user. If it goes through it checks if the input was empty string in which case
             * it also Toasts invalid input. If the username was appropriate it updates the User singleton update to it and calls updatePlayer() and updateUI() methods.
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
             * This method gets called when the user presses the changeAge button. It tries to set the age variable to the int value taken from eAges string value.
             * If it fails it Toasts invalid input. If the value is less than 16 it also Toasts invalid input. In other cases it updates the User singleton with that value
             * and calls updatePlayer() and update() methods.
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
                    updateUI();
                }
            }
        });
        changeWeight.setOnClickListener(new View.OnClickListener() {
            /**
             * This method gets called when the user presses the changeWeight button. It tries to set the weight variable to the int value taken from eWeights string value.
             * If it fails it Toasts invalid input. If the value is less than or equal to 0 it also Toasts invalid input. In other cases it updates the User singleton with that value
             * and calls updatePlayer() and update() methods.
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
                    updateUI();
                }
            }
        });
        changeHeight.setOnClickListener(new View.OnClickListener() {
            /**
             * This method gets called when the user presses the changeHeight button. It tries to set the height variable to the int value taken from eHeight string value.
             * If it fails it Toasts invalid input. If the value is less than or equal to 100 it also Toasts invalid input. In other cases it updates the User singleton with that value
             * and calls updatePlayer() and update() methods.
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
                    updateUI();
                }

            }
        });
        exitSettings.setOnClickListener(new View.OnClickListener() {
            /**
             * This method gets called whenever the user presses the exitSettings button. This only calls updateUI() method and closes the Dialog.
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
             * This method gets called when the user presses the reserData button. It calls the User class method resetData() and calls updatePlayer() method. With this
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
     * This method updates the value of the current Singleton user object to its corresponding userID value in the application data. It puts the singleton data into
     * JSON string and puts it in the appropriate userdata.
     */
    public void updatePlayer(){

        int id = User.getInstance().getId();

        userdata = getSharedPreferences("Userdata", MODE_PRIVATE);
        userDataEdit = userdata.edit();

        Gson gson = new Gson();

        String json = gson.toJson(User.getInstance());

        switch(id){
            case 1:
                userDataEdit.putString("User1", json);
                userDataEdit.commit();
                break;
            case 2:
                userDataEdit.putString("User2", json);
                userDataEdit.commit();
                break;
            case 3:
                userDataEdit.putString("User3", json);
                userDataEdit.commit();
                break;
        }
    }

    /**
     * This method updates the UI in the menu. It updates the TextViews that represent the current User data from the singleton object. It takes the username, age, weight,
     * height, level and relaxingTime variables and presents them appropriately in the UI.
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
     * This is is slightly modified version of the onResume Android lifecycle method. It calls the updateUI method, updating the UI whenever the activity is resumed or after onCreate has been called.
     */
    protected void onResume(){
        super.onResume();
        updateUI();
    }
}