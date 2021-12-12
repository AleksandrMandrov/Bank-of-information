package com.example.myapplication;

public class SimilarSolutions {
    private String name; // название
    private String time;  // столица

    public SimilarSolutions(String name, String time){

        this.name = name;
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}