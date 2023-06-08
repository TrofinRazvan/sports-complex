package com.springboot.sportscomplex.services.subscription;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.exceptions.SubscriptionNotFoundException;
import com.springboot.sportscomplex.models.dto.SubscriptionDTO;
import com.springboot.sportscomplex.models.entities.SubscriptionEntity;
import com.springboot.sportscomplex.repositories.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        SubscriptionEntity subscription = objectMapper.convertValue(subscriptionDTO, SubscriptionEntity.class);
        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscription);
        return objectMapper.convertValue(savedSubscription, SubscriptionDTO.class);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<SubscriptionEntity> subscriptionsFound = subscriptionRepository.findAll();
        List<SubscriptionDTO> subscriptionsFoundDTO = new ArrayList<>();
        subscriptionsFound.forEach(subscriptionEntity -> subscriptionsFoundDTO.add(objectMapper.convertValue(subscriptionEntity, SubscriptionDTO.class)));
        return subscriptionsFoundDTO;
    }

    @Override
    public void deleteSubscriptionById(long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public SubscriptionDTO updateSubscriptionById(long subscriptionId, SubscriptionDTO subscriptionDTO) {
        SubscriptionEntity existingSubscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found with id: " + subscriptionId));

        existingSubscription.setType(subscriptionDTO.getType());
        existingSubscription.setStartDate(subscriptionDTO.getStartDate());
        existingSubscription.setEndDate(subscriptionDTO.getEndDate());
        existingSubscription.setPriceGymSubscription(subscriptionDTO.getPriceGymSubscription());
        existingSubscription.setPriceSwimmingPoolSubscription(subscriptionDTO.getPriceSwimmingPoolSubscription());
        existingSubscription.setPriceSaunaSubscription(subscriptionDTO.getPriceSaunaSubscription());

        SubscriptionEntity updatedSubscription = subscriptionRepository.save(existingSubscription);
        return objectMapper.convertValue(updatedSubscription, SubscriptionDTO.class);
    }
}