package com.springboot.sportscomplex.services.subscription;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.exceptions.SubscriptionNotFoundException;
import com.springboot.sportscomplex.models.dto.SubscriptionDTO;
import com.springboot.sportscomplex.models.entities.SubscriptionEntity;
import com.springboot.sportscomplex.repositories.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ObjectMapper objectMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   ObjectMapper objectMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public SubscriptionDTO addsubscription(SubscriptionDTO subscriptionDTO) {
        SubscriptionEntity subscription = objectMapper.convertValue(subscriptionDTO, SubscriptionEntity.class);
        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscription);
        return objectMapper.convertValue(savedSubscription, SubscriptionDTO.class);
    }

    @Override
    public SubscriptionDTO updateSubscriptionById(Long id, SubscriptionDTO subscriptionDTO) {
        Optional<SubscriptionEntity> optionalSubscriptionEntity = subscriptionRepository.findById(id);
        if (optionalSubscriptionEntity.isPresent()) {
            SubscriptionEntity existingSubscription = optionalSubscriptionEntity.get();
            existingSubscription.setPriceGymSubscription(subscriptionDTO.getPriceGymSubscription());
            existingSubscription.setPriceSaunaSubscription(subscriptionDTO.getPriceSaunaSubscription());
            existingSubscription.setPriceSwimmingPoolSubscription(subscriptionDTO.getPriceSwimmingPoolSubscription());
            SubscriptionEntity updatedSubscription = subscriptionRepository.save(existingSubscription);
            return objectMapper.convertValue(updatedSubscription, SubscriptionDTO.class);
        } else {
            throw new SubscriptionNotFoundException("Subscription not found with ID: " + id);
        }
    }
}
