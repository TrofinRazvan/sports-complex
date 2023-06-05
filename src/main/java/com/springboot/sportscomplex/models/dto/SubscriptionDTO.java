package com.springboot.sportscomplex.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubscriptionDTO implements Serializable {

//    @NotBlank
    private int priceGymSubscription;
//    @NotBlank
    private int priceSwimmingPoolSubscription;
//    @NotBlank
    private int priceSaunaSubscription;
}
