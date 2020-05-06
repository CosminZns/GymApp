package com.cosmin.gym.model;

import java.util.List;

public class Workout {
    private String name;
    private List<Excercise> excercise;
    private int caloriesBurned;
    private String type;


    public Workout(List<Excercise> excercise, int caloriesBurned, String type, String name) {
        this.excercise = excercise;
        this.caloriesBurned = caloriesBurned;
        this.type = type;
        this.name=name;
    }


    public List<Excercise> getExcercise() {
        return excercise;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getType() {
        return type;
    }
    public String getName(){
        return name;
    }
}
