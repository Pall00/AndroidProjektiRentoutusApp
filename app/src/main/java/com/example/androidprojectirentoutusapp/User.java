package com.example.androidprojectirentoutusapp;

public class User {
    /**
     * Have variables age,weight height, name, level, relaxingTime,id and timer.
     *
     *
     *
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

    private User(){
    }

    public static User getInstance(){
        return ourInstance;
    }

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

    public double getBmi(){

        double weight = this.weight;

        double height = (double) this.height/100;

        double bmi = (weight/height/height);

        return bmi;

    }

    public int getAge() {
        return age;
    }

    public int getId(){
        return this.id;
    }

    public void levelUp(){
        if(this.getLevel()<6) {
            this.level += 1;
        }
    }

    public void resetData(){
        this.level = 0;
        this.relaxingTime = 0;
        //this.timer = 60000;
    }

    public long getTimer(){
        return timer;
    }

    public void setTimer(long timer){
        this.timer = timer;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public int getRelaxingTime() {
        return relaxingTime;
    }

    public void addRelaxingTime(int time) {
        this.relaxingTime += time;
    }

}