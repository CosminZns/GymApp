package com.Main;

import Gym.Workout;
import Nutrition.Diet;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String userName;
    private String password;
    private String name;
    private int weight;
    private int goalWeight;
    private List<Workout> workout;
    private Diet diet;


    public User(String userName, String password, String name, int weight, int goalWeight, List<Workout> workout, Diet diet) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.weight = weight;
        this.goalWeight = goalWeight;
        this.diet = diet;
        this.workout = workout;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getGoalWeight() {
        return goalWeight;
    }

    public List<Workout> getWorkout() {
        return workout;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setGoalWeight(int goalWeight) {
        this.goalWeight = goalWeight;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public void setWorkout(List<Workout> workout) {
        this.workout = workout;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return new StringBuffer(" User Name: ").append(this.userName)
                .append("  Name : ").append(this.name).append(" Weight : ").append(this.weight).toString();
    }
}



