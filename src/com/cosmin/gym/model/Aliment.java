package com.cosmin.gym.model;

public class Aliment {
    private int id;
    private String name;
    private int grams;
    private int calories;
    private int carbs;
    private int fat;
    private int protein;

    public Aliment(String name, int grams, int calories, int carbs, int fat, int protein) {
        this.name = name;
        this.grams = grams;
        this.calories = calories;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }

    public Aliment(int id, String name, int grams, int calories, int carbs, int fat, int protein) {
        this.id = id;
        this.name = name;
        this.grams = grams;
        this.calories = calories;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }

    public int getCalories() {
        return calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public int getGrams() {
        return grams;
    }
}
