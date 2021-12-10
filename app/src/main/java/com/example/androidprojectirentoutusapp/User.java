package com.example.androidprojectirentoutusapp;
/**
 * This is a class that implements user singleton that holds all the data taken from the given parts of data
 * The values are used in between different activities of the program and they can be changed throughout the program with different methods
 * @author Juho
 * @version 1.0
 */

public class User {
    private int age;
    private int weight;
    private int height;
    private String name;
    private int level;
    private int relaxingTime;
    private static final User ourInstance = new User();
    private int id;
    private long timer;

    /**
     * This is constructor for creating new user. As it is private, it is only called inside this class
     */
    private User(){
    }

    /**
     * This is a method for accessing the User object in the singleton class so that it can be used throughout the activities.
     * @return ourInstance, which is the singleton object
     */

    public static User getInstance(){
        return ourInstance;
    }

    /**
     * This is a method for setting values to user.
     * @param age the age of the user
     * @param id the user ID that identifies who the user is
     * @param height height of the user in centimeters
     * @param level the current level of the user in the "relaxing" minigame present in the app
     * @param name the name of the user
     * @param relaxingTime the time that has been spent in the "relaxing" minigame in seconds
     * @param timer the timer value. This sets for how long the timer ticks. The value is in milliseconds.
     * @param weight the weight of the user in kilograms
     */
    public void setValues(String name, int age, int weight, int height, int level, int relaxingTime, int id, long timer) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.name = name;
        this.level = level;
        this.relaxingTime = relaxingTime;
        this.id = id;
        this.timer = timer;
    }

    /**
     * This is a method calculates and returns users body mass index
     * @return BMI (body mass index) of the user
     */
    public double getBmi(){

        double weight = this.weight;

        double height = (double) this.height/100;

        double bmi = (weight/height/height);

        return bmi;

    }
    /**
     * This is a method to increment to the users level value
     * if the current value is six which is is the maximum value allowed in the program, it does nothing
     */
    public void levelUp(){
        if(this.getLevel()<6) {
            this.level += 1;
        }
    }
    /**
     * This is a method for resetting users level and relaxingTime back to zero
     */
    public void resetData(){
        this.level = 0;
        this.relaxingTime = 0;
        //this.timer = 60000;
    }
    /**
     * This is a method for returning value of users id variable
     * @return user ID of the user
     */
    public int getId(){
        return this.id;
    }
    /**
     * This is a method for returning value of users timer variable
     * @return returns the value of the timer in milliseconds
     */
    public long getTimer(){
        return timer;
    }
    /**
     * This is a method for setting value of users timer variable
     * @param timer the new value of the timer in milliseconds
     */
    public void setTimer(long timer){
        this.timer = timer;
    }
    /**
     * This is a method for returning value of users age variable
     * @return the users age
     */
    public int getAge() {
        return age;
    }
    /**
     * This is a method for setting value of users age variable
     * This is used to change or initialize the value in certain parts of the program
     * @param age the new age of the user in years.
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * This is a method for returning value of users weight variable
     * @return the weight of the user
     */
    public int getWeight() {
        return weight;
    }
    /**
     * This is a method for setting value of users weight variable
     * This is used to change or initialize the value in certain parts of the program
     * @param weight the new weight of the user
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    /**
     * This is a method for returning value of users height variable
     * @return user height
     */
    public int getHeight() {
        return height;
    }
    /**
     * This is a method for setting value of users height variable
     * This is used to change or initialize the value in certain parts of the program
     * @param height the new height of the user
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * This is a method for returning value of users name variable
     * @return user name
     */
    public String getName() {
        return name;
    }
    /**
     * This is a method for setting value of users name variable
     * This is used to change or initialize the value in certain parts of the program
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This is a method for returning value of users level variable
     * @return the current level of the user
     */
    public int getLevel() {
        return level;
    }
    /**
     * This is a method for returning value of users relaxingTime variable
     * @return the current time spent in the "relaxing" minigame in seconds
     */
    public int getRelaxingTime() {
        return relaxingTime;
    }
    /**
     * This is a method for setting value of users relaxingTime variable
     * @param time adds its value to the current relaxingTime value
     */
    public void addRelaxingTime(int time) {
        this.relaxingTime += time;
    }

}