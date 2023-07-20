package com.springboot.sportscomplex.services.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {


    @Value("${account.sid}")
    private String accountSid;
    @Value("${auth.token}")
    private String authToken;
    @Value("${phone.number}")
    private String fromPhoneNumber;

    @Override
    public void sendSMS(String endDate, String subscriptionType) {

        Twilio.init(accountSid, authToken);
        Message smsMessage = Message.creator(
                new PhoneNumber("+1 928 307 8066"),
                new PhoneNumber(fromPhoneNumber),
                "Thank you, our subscription " + subscriptionType + " it will end on " + endDate).create();
    }
}