package com.springboot.sportscomplex.services.subscription;

import com.springboot.sportscomplex.models.dto.SubscriptionDTO;
import org.springframework.http.ResponseEntity;

public interface SubscriptionService {

    SubscriptionDTO addsubscription(SubscriptionDTO subscriptionDTO);
    SubscriptionDTO updateSubscriptionById(Long id, SubscriptionDTO subscriptionDTO);
}
