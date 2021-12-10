package com.example.androidprojectirentoutusapp;
/**
 *
 * @author Juho
 */

public class User {
    /**
     * This is a class for user.
     * It have variables age,weight height, name, level, relaxingTime,id and timer.
     */
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
     * This is constructor for creating new user.
     */
    private User(){
    }

    public static User getInstance(){
        return ourInstance;
    }

    /**
     * This is a method for setting values to user.
     * It have variables age,weight height, name, level, relaxingTime,id and timer.
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
     * This is a method for user to count and return bmi.
     * It uses weight and height to calculate bmi.
     *After bmi is calculated it will return value bmi.
     */
    public double getBmi(){

        double weight = this.weight;

        double height = (double) this.height/100;

        double bmi = (weight/height/height);

        return bmi;

    }
    /**
     * This is a method for user to levelUp.
     * It checks what level user is and if user is under 6
     * it will level user one level higher.
     */
    public void levelUp(){
        if(this.getLevel()<6) {
            this.level += 1;
        }
    }
    /**
     * This is a method for resetting users level and relaxingTime back to zero.
     */
    public void resetData(){
        this.level = 0;
        this.relaxingTime = 0;
        //this.timer = 60000;
    }
    /**
     * This is a method for returning value of users id variable.
     */
    public int getId(){
        return this.id;
    }
    /**
     * This is a method for returning value of users timer variable.
     */
    public long getTimer(){
        return timer;
    }
    /**
     * This is a method for setting value of users timer variable.
     */
    public void setTimer(long timer){
        this.timer = timer;
    }
    /**
     * This is a method for returning value of users age variable.
     */
    public int getAge() {
        return age;
    }
    /**
     * This is a method for setting value of users age variable.
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * This is a method for returning value of users weight variable.
     */
    public int getWeight() {
        return weight;
    }
    /**
     * This is a method for setting value of users weight variable.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    /**
     * This is a method for returning value of users height variable.
     */
    public int getHeight() {
        return height;
    }
    /**
     * This is a method for setting value of users height variable.
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * This is a method for returning value of users name variable.
     */
    public String getName() {
        return name;
    }
    /**
     * This is a method for setting value of users name variable.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This is a method for returning value of users level variable.
     */
    public int getLevel() {
        return level;
    }
    /**
     * This is a method for returning value of users relaxingTime variable.
     */
    public int getRelaxingTime() {
        return relaxingTime;
    }
    /**
     * This is a method for setting value of users relaxingTime variable.
     */
    public void addRelaxingTime(int time) {
        this.relaxingTime += time;
    }

}