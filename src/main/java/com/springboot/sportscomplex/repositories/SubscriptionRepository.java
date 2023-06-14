package com.springboot.sportscomplex.repositories;

import com.springboot.sportscomplex.models.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}