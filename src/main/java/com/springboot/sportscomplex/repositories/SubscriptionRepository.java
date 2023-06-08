package com.springboot.sportscomplex.repositories;

import com.springboot.sportscomplex.models.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    Optional<SubscriptionEntity> findByPhoneNumber(String phoneNumber);
}