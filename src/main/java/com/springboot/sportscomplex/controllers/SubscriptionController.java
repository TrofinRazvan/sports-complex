package com.springboot.sportscomplex.controllers;

import com.springboot.sportscomplex.models.dto.SubscriptionDTO;
import com.springboot.sportscomplex.services.subscription.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriptionController {

    public final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    @PostMapping("/subscriptions")
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {
        SubscriptionDTO createdSubscription = subscriptionService.createSubscription(subscriptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscription);
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    @DeleteMapping("/subscriptions/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscriptionById(@PathVariable long subscriptionId) {
        subscriptionService.deleteSubscriptionById(subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/subscriptions/{subscriptionId}")
    public ResponseEntity<SubscriptionDTO> updateSubscriptionById(@PathVariable long subscriptionId, @RequestBody @Valid SubscriptionDTO subscriptionDTO) {
        SubscriptionDTO updatedSubscription = subscriptionService.updateSubscriptionById(subscriptionId, subscriptionDTO);
        return ResponseEntity.ok(updatedSubscription);
    }
}