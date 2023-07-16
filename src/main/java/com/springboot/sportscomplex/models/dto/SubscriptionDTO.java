package com.springboot.sportscomplex.models.dto;

//import com.springboot.sportscomplex.models.entities.SubscriptionType;
//import jakarta.persistence.Column;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
import com.springboot.sportscomplex.models.entities.SubscriptionType;
import jakarta.persistence.*;
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
    @NotNull(message = "Subscription type must not be null.")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
}