package com.springboot.sportscomplex.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscriptionDTO implements Serializable {

    private double priceGymSubscription;
    private double priceSwimmingPoolSubscription;
    private double priceSaunaSubscription;
}