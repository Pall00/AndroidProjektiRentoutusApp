package com.example.androidprojectirentoutusapp;

public class Timer {

    private int seconds;
    private int minutes;

    public Timer(long MS){
        int seconds =(int) MS/1000;
        this.minutes = seconds % 3600 /60;
        this.seconds = seconds % 60;
    }
    public void plusMinutes(){
        if(this.minutes<59){
            this.minutes+=1;
        }
        else{
            this.minutes=0;
        }
    }
    public void minusMinutes(){
        if(this.minutes>0){
            this.minutes-=1;
        }
        else{
            this.minutes=59;
        }
    }
    public void plusSeconds(){
        if(this.seconds<59){
            this.seconds+=1;
        }
        else{
            this.seconds=0;
        }
    }
    public void minusSeconds(){
        if(this.seconds>0){
            this.seconds-=1;
        }
        else{
            this.seconds=59;
        }
    }

    public int getSeconds() {
        return seconds;
    }
    public int getMinutes() {
        return minutes;
    }

    public long getMS(){
        long minutesMS = (long) this.minutes * 60000;
        long secondsMS = (long) this.seconds * 1000;
        return (minutesMS + secondsMS);
    }
}
