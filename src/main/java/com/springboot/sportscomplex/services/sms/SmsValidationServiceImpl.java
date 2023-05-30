package com.springboot.sportscomplex.services.sms;

import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class SmsValidationServiceImpl implements SmsValidationService {

    @Override
    public boolean smsValidation(String sms) {
        String regex = "^\\d{3}-\\d{3}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(sms).matches();
    }
}