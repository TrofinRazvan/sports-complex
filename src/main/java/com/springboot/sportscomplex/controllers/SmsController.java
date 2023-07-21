package com.springboot.sportscomplex.controllers;

import com.springboot.sportscomplex.models.entities.Subscription;
import com.springboot.sportscomplex.services.sms.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final SmsService smsService;
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendSMS(@RequestBody Subscription subscription) {
        String endDate = subscription.getEndDate().toString();
        String subscriptionType = subscription.getSubscriptionType().toString();
        smsService.sendSMS(endDate, subscriptionType);
        return ResponseEntity.ok("SMS sent succesfully!");
    }
}