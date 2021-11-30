package com.example.androidprojectirentoutusapp;

public class User {
    private int age;
    private int weight;
    private int height;
    private String name;
    private int level;
    private int meditationTime;
    private static final User ourInstance = new User();

    private User(){
    }

    public static User getInstance(){
        return ourInstance;
    }

    public void setValues(int age, int weight, int height, String name, int level, int meditationTime) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.name = name;
        this.level = 0;
        this.meditationTime = 0;
    }

    public int getBmi(){
        return (this.weight/(this.height*this.height));
    }

    public int getAge() {
        return age;
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
