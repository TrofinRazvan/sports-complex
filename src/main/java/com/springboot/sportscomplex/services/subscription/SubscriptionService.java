package com.springboot.sportscomplex.services.subscription;

import com.springboot.sportscomplex.models.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO);

    List<SubscriptionDTO> getAllSubscriptions();

    void deleteSubscriptionById(long subcriptionId);

    SubscriptionDTO updateSubscriptionById(long subcriptionId, SubscriptionDTO subscriptionDTO);
}