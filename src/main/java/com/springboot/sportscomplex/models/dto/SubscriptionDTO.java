package com.springboot.sportscomplex.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubscriptionDTO implements Serializable {

    @NotBlank
    private String priceGymSubscription;
    @NotBlank
    private String priceSwimmingPoolSubscription;
    @NotBlank
    private String priceSaunaSubscription;
}
