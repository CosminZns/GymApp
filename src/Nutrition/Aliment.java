package Nutrition;

public class Aliment {
    private final String name;
    private final int grams;
    private final int kcal;
    private final int carbs;
    private final int fat;
    private final int protein;

    public Aliment(String name, int grams, int kcal, int carbs, int fat, int protein) {
        this.name = name;
        this.grams = grams;
        this.kcal = kcal;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }

    public int getKcal() {
        return kcal;
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
