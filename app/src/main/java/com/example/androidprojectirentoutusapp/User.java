package com.example.androidprojectirentoutusapp;

import com.google.gson.JsonElement;

public class User {
    private int age;
    private int weight;
    private int height;
    private String name;
    private int level;
    private int meditationTime;
    private static final User ourInstance = new User();
    private int id;

    private User(){
    }

    public static User getInstance(){
        return ourInstance;
    }

    public void setValues(int age, int weight, int height, String name, int level, int meditationTime, int id) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.name = name;
        this.level = level;
        this.meditationTime = meditationTime;
        this.id = id;
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
        if(this.getLevel()<7) {
            this.level += 1;
        }
    }

    public void resetData(){
        this.level = 0;
        this.meditationTime = 0;
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

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMeditationTime() {
        return meditationTime;
    }

    public void setMeditationTime(int meditationTime) {
        this.meditationTime = meditationTime;
    }

}
