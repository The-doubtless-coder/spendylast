package com.spenndify.application.spendylast.onboarding.Twilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spendy/user")
public class Controller {

    private final TwilioSmsSender twilioSmsSender;

    @Autowired
    public Controller(TwilioSmsSender twilioSmsSender) {
        this.twilioSmsSender = twilioSmsSender;
    }

//    @PostMapping
//    public void sendSms(@RequestBody SmsDto smsDto) {
//        twilioSmsSender.sendSms(phone);
//    }
}

