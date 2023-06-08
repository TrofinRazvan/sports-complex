package com.springboot.sportscomplex.services.subscription;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.exceptions.SubscriptionNotFoundException;
import com.springboot.sportscomplex.models.dto.SubscriptionDTO;
import com.springboot.sportscomplex.models.entities.SubscriptionEntity;
import com.springboot.sportscomplex.models.entities.UserEntity;
import com.springboot.sportscomplex.repositories.SubscriptionRepository;
import com.springboot.sportscomplex.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   ObjectMapper objectMapper,
                                   UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public SubscriptionDTO addSubscription(String phoneNumber, SubscriptionDTO subscriptionDTO) {
        SubscriptionEntity subscription = objectMapper.convertValue(subscriptionDTO, SubscriptionEntity.class);
        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscription);
        return objectMapper.convertValue(savedSubscription, SubscriptionDTO.class);
    }

    @Override
    public SubscriptionDTO updateSubscriptionByPhoneNumber(String phoneNumber, SubscriptionDTO subscriptionDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUserEntity.isPresent()) {
            UserEntity user = optionalUserEntity.get();
            List<SubscriptionEntity> subscriptions = user.getSubscriptions();
            if (!subscriptions.isEmpty()) {
                SubscriptionEntity subscription = subscriptions.get(0);
                subscription.setPriceGymSubscription(subscriptionDTO.getPriceGymSubscription());
                subscription.setPriceSaunaSubscription(subscriptionDTO.getPriceSaunaSubscription());
                subscription.setPriceSwimmingPoolSubscription(subscriptionDTO.getPriceSwimmingPoolSubscription());
                SubscriptionEntity updatedSubscription = subscriptionRepository.save(subscription);
                return objectMapper.convertValue(updatedSubscription, SubscriptionDTO.class);
            } else {
                throw new SubscriptionNotFoundException("No subscriptions found for user with phone number: " + phoneNumber);
            }
        } else {
            throw new SubscriptionNotFoundException("User not found with phone number: " + phoneNumber);
        }
    }
}