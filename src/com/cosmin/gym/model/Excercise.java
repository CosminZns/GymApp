package com.cosmin.gym.model;


public class Excercise {
    private String group;
    private String name;

    public Excercise(String name, String group) {
        this.name = name;
        this.group = group;

    }

    public String getName() {
        return name;
    }


    public String getGroup() {
        return group;
    }
}
