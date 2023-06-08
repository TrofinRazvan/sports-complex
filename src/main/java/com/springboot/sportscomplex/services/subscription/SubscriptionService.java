package com.springboot.sportscomplex.services.subscription;

import com.springboot.sportscomplex.models.dto.SubscriptionDTO;

public interface SubscriptionService {

    SubscriptionDTO addSubscription(String phoneNumber, SubscriptionDTO subscriptionDTO);
    SubscriptionDTO updateSubscriptionByPhoneNumber(String phoneNumber, SubscriptionDTO subscriptionDTO);
}