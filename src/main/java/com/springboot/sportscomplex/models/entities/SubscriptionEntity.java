package com.springboot.sportscomplex.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subscriptons")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "price_gym_membership")
    private int priceGymSubscription;
    @Column(name = "price_swimming_pool_subscription")
    private int priceSwimmingPoolSubscription;
    @Column(name = "price_sauna_subscription")
    private int priceSaunaSubscription;
}
