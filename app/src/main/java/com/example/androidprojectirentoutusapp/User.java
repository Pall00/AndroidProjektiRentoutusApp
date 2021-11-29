package com.example.androidprojectirentoutusapp;

public class User {
    private int age;
    private int weight;
    private int height;
    private String name;
    private int level;
    private int meditationTime;

    public User(int age, int weight, int height, String name) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.name = name;
        this.level = level;
    }
    public User(){

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
