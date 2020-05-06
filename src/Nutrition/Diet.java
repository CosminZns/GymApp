package Nutrition;

import java.util.ArrayList;

public class Diet {
    private String type;
    private String name;
    private ArrayList<Aliment> diets ;
    private int kcal;


    public Diet(ArrayList<Aliment> diets, int kcal, String name, String type) {
        this.name = name;
        this.diets = diets;
        this.kcal = kcal;
        this.type = type;
    }

    public ArrayList<Aliment> getDiets() {
        return diets;
    }

    public int getKcal() {
        return kcal;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }
}
