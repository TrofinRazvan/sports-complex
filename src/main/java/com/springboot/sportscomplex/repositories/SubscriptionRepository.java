package com.springboot.sportscomplex.repositories;

import com.springboot.sportscomplex.models.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

}