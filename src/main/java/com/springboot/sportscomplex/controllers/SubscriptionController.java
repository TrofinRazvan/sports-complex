package com.springboot.sportscomplex.controllers;

import com.springboot.sportscomplex.models.dto.SubscriptionDTO;
import com.springboot.sportscomplex.services.subscription.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {

    public final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    @PostMapping("/subscriptions")
    public ResponseEntity<SubscriptionDTO> addSubscription(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.addSubscription(subscriptionDTO));
    }
    @PutMapping("/subscriptions/{phoneNumber}")
    public ResponseEntity<SubscriptionDTO> updateSubscriptionByPhoneNumber(@PathVariable String phoneNumber, @RequestBody @Valid SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.updateSubscriptionByPhoneNumber(phoneNumber, subscriptionDTO));
    }
}