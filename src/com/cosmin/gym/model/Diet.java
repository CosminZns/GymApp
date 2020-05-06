package com.cosmin.gym.model;

import java.util.ArrayList;

public class Diet {
    private String type;
    private String name;
    private ArrayList<Aliment> diets ;
    private int calories;


    public Diet(ArrayList<Aliment> diets, int calories, String name, String type) {
        this.name = name;
        this.diets = diets;
        this.calories = calories;
        this.type = type;
    }

    public ArrayList<Aliment> getDiets() {
        return diets;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }
}
