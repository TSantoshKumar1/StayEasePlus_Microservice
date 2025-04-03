package com.service.notification.smsservice;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account-sid}")
    private String accountSid;
    @Value("${twilio.auth-token}")
    private String authToken;
    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    public TwilioService(){

        Twilio.init(accountSid,authToken);
    }

    public void sendSmsService(String to , String message){

        Message.creator(new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                message).
                create();
    }
}
