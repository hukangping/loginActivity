package com.example.loginactivity;

public class Fruit {
    private String fruitName;
    private int fruitId;

    public String getFruitName() {
        return fruitName;
    }

    public int getFruitId() {
        return fruitId;
    }

    public Fruit(String fruitName, int fruitId) {
        this.fruitName = fruitName;
        this.fruitId = fruitId;
    }
}
