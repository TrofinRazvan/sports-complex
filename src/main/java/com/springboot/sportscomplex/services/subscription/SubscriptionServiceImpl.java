package com.springboot.sportscomplex.services.subscription;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.exceptions.SubscriptionNotFoundException;
import com.springboot.sportscomplex.exceptions.UserNotFoundException;
import com.springboot.sportscomplex.models.dto.SubscriptionDTO;
import com.springboot.sportscomplex.models.entities.Subscription;
import com.springboot.sportscomplex.models.entities.User;
import com.springboot.sportscomplex.repositories.SubscriptionRepository;
import com.springboot.sportscomplex.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   ObjectMapper objectMapper,
                                   UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = objectMapper.convertValue(subscriptionDTO, Subscription.class);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return objectMapper.convertValue(savedSubscription, SubscriptionDTO.class);
    }

    @Override
    public void addSubscriptionToUser(Long userId, Long subscriptionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        user.getSubscriptions().add(subscription);
        subscription.getUsers().add(user);
        userRepository.save(user);
        subscriptionRepository.save(subscription);
    }

    //    @Override
//    public void addSubscriptionToUserUsingPhoneNumber(String phoneNumber, Long subscriptionId) {
//        User user = userRepository.findUserByPhoneNumber(phoneNumber)
//                .orElseThrow(() -> new PhoneNumberTakenException("Phone number not found."));
//        Subscription subscription = subscriptionRepository.findById(subscriptionId)
//                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
//        user.getSubscriptions().add(subscription);
//        subscription.getUsers().add(user);
//        userRepository.save(user);
//        subscriptionRepository.save(subscription);
//    }
    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<Subscription> subscriptionsFound = subscriptionRepository.findAll();
        List<SubscriptionDTO> subscriptionsFoundDTO = new ArrayList<>();
        subscriptionsFound.forEach(subscription -> subscriptionsFoundDTO.add(objectMapper.convertValue(subscription, SubscriptionDTO.class)));
        return subscriptionsFoundDTO;
    }

    @Override
    public void deleteSubscriptionById(long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public SubscriptionDTO updateSubscriptionById(long subscriptionId, SubscriptionDTO subscriptionDTO) {
        Subscription existingSubscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found with id: " + subscriptionId));

        existingSubscription.setType(subscriptionDTO.getType());
        existingSubscription.setStartDate(subscriptionDTO.getStartDate());
        existingSubscription.setEndDate(subscriptionDTO.getEndDate());
        existingSubscription.setSubscriptionType(subscriptionDTO.getSubscriptionType());
//        existingSubscription.setPriceGymSubscription(subscriptionDTO.getPriceGymSubscription());
//        existingSubscription.setPriceSwimmingPoolSubscription(subscriptionDTO.getPriceSwimmingPoolSubscription());
//        existingSubscription.setPriceSaunaSubscription(subscriptionDTO.getPriceSaunaSubscription());

        Subscription updatedSubscription = subscriptionRepository.save(existingSubscription);
        return objectMapper.convertValue(updatedSubscription, SubscriptionDTO.class);
    }
}