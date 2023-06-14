package com.springboot.sportscomplex.models.entities;

public enum SubscriptionType {

    GYM(200.0),
    SWIMMING_POOL(250.0),
    SAUNA(150.0);

    private final double price;

    SubscriptionType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}