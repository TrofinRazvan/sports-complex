package com.springboot.sportscomplex.controllers;

import com.springboot.sportscomplex.models.dto.SmsDTO;
import com.springboot.sportscomplex.services.sms.SmsValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/sms")
public class SmsController {

    private final SmsValidationService smsValidationService;

    @Autowired
    public SmsController(SmsValidationService smsValidationService) {
        this.smsValidationService = smsValidationService;
    }

    @PostMapping
    public String processSms(@RequestBody @Valid SmsDTO smsDTO) {
        boolean isValid = smsValidationService.smsValidation(String.valueOf(smsDTO));
        if (isValid) {
            return "SMS-ul este valid.";
        }
        return "SMS-ul nu este valid.";

    }
}
