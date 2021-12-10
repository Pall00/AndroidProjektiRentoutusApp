package com.example.androidprojectirentoutusapp;

/**
 * This is a class that is used to easily change the User classes timer data.
 * Its job is to hold integer values of second and minutes and be able to convert those values into long datatype.
 * This class is not an independent timer and is only used for editing the User classes data that determines the values of the timer in the program itself.
 * @author Santeri
 * @version 1.0
 */

public class Timer {

    private int seconds;
    private int minutes;

    /**
     * This is a public constructor for the Timer class. It gets a long value, processes it and initializes class variables.
     * @param MS the long value that represents the wanted initial timer time value in milliseconds, hence the name.
     */

    public Timer(long MS){
        int seconds =(int) MS/1000;
        this.minutes = seconds % 3600 /60;
        this.seconds = seconds % 60;
    }

    /**
     * This is a method for incrementing 1 minute to the timer object.
     * If the current value of the the timers minutes variable is 59, it goes to zero since 60 minutes is 1 hour and holding it in minutes is not ideal.
     */

    public void plusMinutes(){
        if(this.minutes<59){
            this.minutes+=1;
        }
        else{
            this.minutes=0;
        }
    }

    /**
     *This is a method for decrementing 1 minute from the timer object.
     * If the current value of the timers minutes variable is 0, it goes to 59 as minutes can't be negative.
     */

    public void minusMinutes(){
        if(this.minutes>0){
            this.minutes-=1;
        }
        else{
            this.minutes=59;
        }
    }

    /**
     * This is a method for incrementing 1 second to the timer object.
     * If the current value of the the timers minutes variable is 59, it goes to zero since 60 second is 1 minute and holding it in seconds is not ideal.
     */

    public void plusSeconds(){
        if(this.seconds<59){
            this.seconds+=1;
        }
        else{
            this.seconds=0;
        }
    }

    /**
     *This is a method for decrementing 1 second from the timer object.
     * If the current value of the timers seconds variable is 0, it goes to 59 as seconds can't be negative.
     */

    public void minusSeconds(){
        if(this.seconds>0){
            this.seconds-=1;
        }
        else{
            this.seconds=59;
        }
    }

    /**
     * This is a method for returning the current value of seconds variable so that the program can access the data and use it.
     * @return the Timers current value of seconds variable.
     */

    public int getSeconds() {
        return seconds;
    }

    /**
     * This is a method for returning the current value of minutes variable so that the program can access the data and use it.
     * @return the Timers current value of minutes variable.
     */

    public int getMinutes() {
        return minutes;
    }

    /**
     * This is a method for getting the combined values of minutes and seconds variables in milliseconds.
     * The method makes both the seconds and minutes into long datatypes to represent them in milliseconds and adds them together to get the result.
     * @return the current value of the timer in milliseconds.
     */

    public long getMS(){
        long minutesMS = (long) this.minutes * 60000;
        long secondsMS = (long) this.seconds * 1000;
        return (minutesMS + secondsMS);
    }
}
