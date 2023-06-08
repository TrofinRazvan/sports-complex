package com.springboot.sportscomplex.models.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SubscriptionDTO implements Serializable {

    @NotBlank(message = "It must be a monthly subscription or an annual subscription.")
    private String type;
    @NotNull(message = "Start date must be specified.")
    @FutureOrPresent(message = "Start date must be in the present or future.")
    private LocalDate startDate;
    @NotNull(message = "End date must be specified.")
    @FutureOrPresent(message = "End date must be in the present or future.")
    private LocalDate endDate;
    @NotNull(message = "Price must not be null.")
    @Positive(message = "Gym subscription price must be a positive value.")
    private double priceGymSubscription;
    @NotNull(message = "Price must not be null.")
    @Positive(message = "Swimming pool subscription price must be a positive value.")
    private double priceSwimmingPoolSubscription;
    @NotNull(message = "Price must not be null.")
    @Positive(message = "Sauna subscription price must be a positive value.")
    private double priceSaunaSubscription;
}