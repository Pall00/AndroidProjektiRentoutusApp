package com.example.RentoutumisAppRentFree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * This is the Relaxing class. This is the class for the "relaxing" minigame. It uses the current User singleton data and updates User data specific to the relaxing
 * minigame.
 * @author Santeri Rytkönen
 * @author Juho Ahola
 * @version 1.0
 */

public class Relaxing extends AppCompatActivity {

    private TextView textViewTimer;
    private boolean timerOn, rabbit;
    private Dialog exitDialog, timerDialog;
    private int timeValue, minutes, clockInSeconds, seconds, rabbithits;
    private MediaPlayer mediaPlayer, soundRabbitNormal, soundRabbitDeath;
    private long clock = User.getInstance().getTimer();
    private SharedPreferences userdata;
    private SharedPreferences.Editor userdataedit;
    private CountDownTimer timer;

    /**
     * This is the onCreate method. It does the default method while also assigning the boolean value of rabbit to true, setting the rabbithits value to 0, timerOn boolean value to  false,
     * and setting up the MediaPlayers while also updating the UI with the updateBackground(), updateRabbit() and updateButton() methods. It also makes toast to inform the user that the sun texture is intractable.
     * @param savedInstanceState is a bundle object that onCreate takes as a parameter. The bundle is used to save stored data of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing);
        rabbit = true;
        rabbithits=0;
        timerOn = false;
        timeValue = 0;
        soundRabbitDeath = MediaPlayer.create(getApplicationContext(), R.raw.oofdeath);
        soundRabbitNormal = MediaPlayer.create(getApplicationContext(), R.raw.oofnormal);
        updateBackground();
        updateRabbit();
        updateButton();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.appmusic);
        Toast.makeText(getApplicationContext(), "Press the sun to begin relaxing", Toast.LENGTH_SHORT).show();

    }

    /**
     * This methods job is to stop the CountDownTimer used in the program and set the textViewTimer to empty string. It also adds the remaining of the timeValue to the singleton User object.
     */
    public void stopTimer() {
        timerOn = false;
        timer.cancel();
        textViewTimer = findViewById(R.id.tvTimer);
        textViewTimer.setText("");
        User.getInstance().addRelaxingTime(timeValue);
        timeValue = 0;
    }

    /**
     * This is the method that gets activated when the user clicks the sun. First the method checks if the timerOn value is not true, meaning that the timer is not already on.
     * If the timer is on it Toast a message to the user telling that the timer is already on. If the timer is not on, it starts the mediaPlayer to play music and starts the timer. It also turns the timerOn value to true.
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void sunTimerButton(View v) {
        textViewTimer = findViewById(R.id.tvTimer);
        if (!timerOn) {
            mediaPlayer.start();
            timer = new CountDownTimer(clock, 1000) {
                /**
                 * This is the onTick method. This gets executed on every tick. On every tick the method updates the value of the Timer in minutes and seconds and puts the values in the appropriate TextView.
                 * @param millisUntilFinished this parameter represents the amount of milliseconds the CountDownTimer has left before it finishes.
                 */
                @Override
                public void onTick(long millisUntilFinished) {
                    clockInSeconds = (int) (millisUntilFinished / 1000);
                    minutes = clockInSeconds % 3600 / 60;
                    seconds = clockInSeconds % 60;
                    String time = String.format("%02d:%02d", minutes, seconds);
                    textViewTimer.setText("Time remaining: " + time);
                    timeValue = (int)  (((clock +1000)-millisUntilFinished) / 1000);
                }

                /**
                 * This method gets called whenever the CountDownTimer finishes normally, meaning the millisUntilFinished gets to zero.
                 * When it finishes it pauses the music, sets the TextView to empty string and checks the current user level and puts out appropriate Toast text.
                 * It then calls the levelUp() method from the User, incrementing the Users level by one and it also adds the timeValue to the Users relaxing time that represents the time that the CountDownTimer had.
                 * At the end it puts the timerOn value to false, resets the timeValue to zero and calls updateAll() method.
                 */
                @Override
                public void onFinish() {
                    mediaPlayer.pause();
                    textViewTimer.setText("");
                    if(User.getInstance().getLevel() == 2 && timerOn){
                        Toast.makeText(getApplicationContext(), "You got yourself a bunny!", Toast.LENGTH_SHORT).show();
                    }
                    else if(User.getInstance().getLevel() <5 && timerOn){
                        Toast.makeText(getApplicationContext(), "You made it to the next level! New trivia available!", Toast.LENGTH_SHORT).show();
                    }
                    else if(User.getInstance().getLevel() <6 && timerOn){
                        Toast.makeText(getApplicationContext(), "Level up! Timer settings unlocked. You can now change the timer yourself. There is also a new trivia!", Toast.LENGTH_LONG).show();
                    }
                    else if (timerOn){
                        Toast.makeText(getApplicationContext(), "You finished your session! Keep up the good work!", Toast.LENGTH_SHORT).show();
                    }
                    if (timerOn) {
                        User.getInstance().levelUp();
                        User.getInstance().addRelaxingTime(timeValue);
                    }
                    timerOn = false;
                    timeValue = 0;
                    updateAll();

                }
            }.start();
        }
        else{
            Toast.makeText(getApplicationContext(), "Timer is already on! Relax!", Toast.LENGTH_SHORT).show();
        }
        timerOn = true;
    }

    /**
     * This method is a way to update everything on the screen and update all the User values to the app data. It calls all the update methods and executes them.
     */
    public void updateAll(){
        updateBackground();
        updatePlayer();
        updateRabbit();
        updateButton();
    }

    /**
     * This is the method to update the background for the activity. It checks the users current level and sets the ImageView of the level to the value it represents.
     */
    public void updateBackground(){
        int level = User.getInstance().getLevel();
        ImageView img = findViewById(R.id.relaxingBG);
        switch(level){
            case 0:
                img.setImageResource(R.drawable.level1);
                break;
            case 1:
                img.setImageResource(R.drawable.level2);
                break;
            case 2:
                img.setImageResource(R.drawable.level3);
                break;
            case 3:
                img.setImageResource(R.drawable.level4);
                break;
            case 4:
                img.setImageResource(R.drawable.level5);
                break;
            case 5:
                img.setImageResource(R.drawable.level6);
                break;
            case 6:
                img.setImageResource(R.drawable.level7);
                break;
        }
    }

    /**
     * This method updates the settings button that appears on the last level in the minigame. If the user is not on the last level, the buttons visibility is set to
     * GONE. GONE means that it can't be clicked nor seen. If the user is on the final evel the settings button appears by making it VISIBLE. It can then be clicked
     * and seen.
     */
    public void updateButton(){
        View button = findViewById(R.id.timerSettings);
        if(User.getInstance().getLevel() !=6){
            button.setVisibility(View.GONE);
        }
        else{
            button.setVisibility(View.VISIBLE);
        }
    }

    /**
     * This method takes the current userID of the singleton object and saves the singleton data to the data the ID represents.
     * It also updates the clock value to the current user timerValue.
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
                break;
            case 2:
                userdataedit.putString("User2", json);
                break;
            case 3:
                userdataedit.putString("User3", json);
                break;
        }
        userdataedit.commit();
        clock = User.getInstance().getTimer();
    }

    /**
     * This method updates the rabbit image on the activity. If the current leevel of the user is higher than 2 and boolean value of rabbit is true, it sets the
     * ImageView imgRabbit to the corresponding picture according to the BMI value taken with the User classes getBmi() method. If the users level is not higher than 2,
     * the image resource is set to zero meaning that there is no Image.
     */
    public void updateRabbit(){

        double bmi = User.getInstance().getBmi();

        ImageView imgRabbit = (ImageView) findViewById(R.id.pupuView);

        if(User.getInstance().getLevel()>2) {
            if(rabbit) {

                if (bmi < 19) {
                    imgRabbit.setImageResource(R.drawable.laihapupu);
                }
                else if (bmi < 25) {
                    imgRabbit.setImageResource(R.drawable.pupu);
                }
                else {
                    imgRabbit.setImageResource(R.drawable.pupupullukka);
                }
            }
            else{
                imgRabbit.setImageResource(R.drawable.pupuluuranko);
            }
        }
        else{
            imgRabbit.setImageResource(0);
        }
    }

    /**
     * This is the method that gets executed when user presses the back button on their phone. If the timerOn value is false, it executes the normal methods and exists the current aactivity.
     * If the timerOn value is true, meaning that the timer on the method puts out a Dialog screen with intractable buttons.
     */
    public void onBackPressed(){
        if(!timerOn){
            super.onBackPressed();
        }
        else{
            exitDialog = new Dialog(this);

            exitDialog.setContentView(R.layout.makingsurepopup);
            ImageButton yesButton = (ImageButton) exitDialog.findViewById(R.id.yesButton);
            ImageButton noButton = (ImageButton) exitDialog.findViewById(R.id.noButton);
            Button closeButton = (Button) exitDialog.findViewById(R.id.closeButton);

            yesButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets executed when the user presses the yesButton on the exitDialog. It closes the Dialog, updates the user relaxing time value by incrementing to it with the timeValues value.
                 * It then sets the timerOn value to false, timer being then off, stops the music and finishes the entire activity.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched.
                 */
                @Override
                public void onClick(View v) {
                    exitDialog.dismiss();
                    User.getInstance().addRelaxingTime(timeValue);
                    updatePlayer();
                    timerOn = false;
                    mediaPlayer.stop();
                    finish();
                }
            });
            noButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets called when the user presses the noButton on the exitDialog. It only closes the Dialog and does nothing else.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched.
                 */
                @Override
                public void onClick(View v) {
                    exitDialog.dismiss();
                }
            });
            closeButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets called when the user presses the closeButton on the exitDialog. It does the same thing as noButton and only closes the Dialog.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched.
                 */
                @Override
                public void onClick(View v) {
                    exitDialog.dismiss();
                }
            });
            exitDialog.show();
        }
    }

    /**
     * This modified onPause() method does the default methods and also checks if the timerOn value is true. If it is it paues the mediaPlayer and stops the timer.
     * This way if the user goes to homescreen, the timer closes and music stops.
     */
    protected void onPause(){
        super.onPause();
        if(timerOn){
            mediaPlayer.pause();
            stopTimer();
        }
    }

    /**
     * This method checks if the timerOn value is true. If it is it then it makesa a Toast message to indicate for the user to relax since the timer is on.
     * If the value is false, it starts p the timerDialog and makes a new Timer object and setsup the timerDialog buttons.
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void timerSettings(View v){
        if(!timerOn){

            timerDialog = new Dialog(this);
            timerDialog.setContentView(R.layout.relaxtimesettings);

            Timer timer = new Timer(User.getInstance().getTimer());

            ImageButton buttonConfirm = (ImageButton) timerDialog.findViewById(R.id.buttonConfirmTime);
            ImageButton buttonSecondsMinus = (ImageButton) timerDialog.findViewById(R.id.buttonSecondsMinus);
            ImageButton buttonSecondsPlus = (ImageButton) timerDialog.findViewById(R.id.buttonSecondsPlus);
            ImageButton buttonMinutesPlus = (ImageButton) timerDialog.findViewById(R.id.buttonMinutesPlus);
            ImageButton buttonMinutesMinus = (ImageButton) timerDialog.findViewById(R.id.buttonMinutesMinus);

            Button buttonClose = (Button) timerDialog.findViewById(R.id.closeTimeSettingsButton);
            updateTimerMenu(timer.getMinutes(), timer.getSeconds());
            buttonConfirm.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets called when the user presses the buttonConfirm button. It checks the current value of the timer.getMS() and if it is not zero, it
                 * updates the User singleton with that value and calls updatePlayer() and closes the timerDialog. If it is zero then it makes a Toast to the user
                 * saying that the timers value can't be zero.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
                 */
                @Override
                public void onClick(View v) {
                    if(timer.getMS()!=0){
                        User.getInstance().setTimer(timer.getMS());
                        updatePlayer();
                        timerDialog.dismiss();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Timer can't be zero!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            buttonClose.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets called when the user presses the buttonClose button. It only closes the current Dialog.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
                 */
                @Override
                public void onClick(View v) {
                    timerDialog.dismiss();
                }
            });
            buttonMinutesPlus.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets called when the user presses the buttonMinutesPlus button. It calls the timer objects plusMinutes() method and calls updateTimerMenu()
                 * with the timer getters as its parameters.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
                 */
                @Override
                public void onClick(View v) {
                    timer.plusMinutes();
                    updateTimerMenu(timer.getMinutes(),timer.getSeconds());
                }
            });
            buttonMinutesMinus.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets called when the user presses the buttonMinutesMinus button. It calls the timer objects minusMinutes() method and calls updateTimerMenu()
                 * with the timer getters as its parameters.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
                 */
                @Override
                public void onClick(View v) {
                    timer.minusMinutes();
                    updateTimerMenu(timer.getMinutes(), timer.getSeconds());
                }
            });
            buttonSecondsMinus.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets called when the user presses the buttonSecondsMinus button. It calls the timer objects minusSeconds() method and calls updateTimerMenu()
                 * with the timer getters as its parameters.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
                 */
                @Override
                public void onClick(View v) {
                    timer.minusSeconds();
                    updateTimerMenu(timer.getMinutes(), timer.getSeconds());
                }
            });
            buttonSecondsPlus.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method gets called when the user presses the buttonSecondsPlus button. It calls the timer objects plusSeconds() method and calls updateTimerMenu()
                 * with the timer getters as its parameters.
                 * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
                 */
                @Override
                public void onClick(View v) {
                    timer.plusSeconds();
                    updateTimerMenu(timer.getMinutes(), timer.getSeconds());
                }
            });
            timerDialog.show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Relax first", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method updates the timerMenu in the timerDialog.
     * @param minutes is an integer that represents the current amount of minutes in the timerDialogs timerMenu
     * @param seconds is an integer that represents the current amount of seconds in the timerDialogs timerMenu
     */
    public void updateTimerMenu(int minutes, int seconds){
        TextView textViewMinutes = (TextView) timerDialog.findViewById(R.id.textViewTimeMinutes);
        TextView textViewSeconds = (TextView) timerDialog.findViewById(R.id.textViewTimeSeconds);
        textViewMinutes.setText(Integer.toString(minutes));
        textViewSeconds.setText(Integer.toString(seconds));
    }

    /**
     * This method gets called whenever the uses presses the rabbit image when the rabbit booleans value is true, meaning that the rabbit exists and is not gone or not present.
     * It first checks if the current value of rabbithits is 6 and rabbit exists. If both of them are true, it updates the rabbit boolean to false, plays the soundRabbitDeath
     * MediaPlayer and calls the updateRabbit() method. The method then checks if the current user level is higher than 2 and if the rabbit boolean is true. If both of these apply,
     * the method increments the rabbithits parameter by one and plays the soundRabbitNormal MediaPlayer.
     * @param v is the object of the XML-file referred on the onCreate method. With it the program can figure what the user touched
     */
    public void rabbitPress(View v){
        if(rabbithits==6 && rabbit){
            rabbit = false;
            soundRabbitDeath.start();
            updateRabbit();
        }
        if(User.getInstance().getLevel() > 2 && rabbit){
            rabbithits+=1;
            soundRabbitNormal.start();
        }
    }

}