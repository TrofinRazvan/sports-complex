package com.springboot.sportscomplex.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "subscriptons")

public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "price_gym_membership")
    private double priceGymSubscription;
    @Column(name = "price_swimming_pool_subscription")
    private double priceSwimmingPoolSubscription;
    @Column(name = "price_sauna_subscription")
    private double priceSaunaSubscription;
    @ManyToMany(mappedBy = "subscriptions", cascade = CascadeType.ALL)
    private List<UserEntity> users;
}