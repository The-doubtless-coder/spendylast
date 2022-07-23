package com.spenndify.application.spendylast.Twilio;

import com.spenndify.application.spendylast.Twilio.Config.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("twilio")
public class TwilioSmsSender{

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    public void sendSms(String phone, String message) {
        if (isPhoneNumberValid(phone)) {
            PhoneNumber to = new PhoneNumber(phone);
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", phone);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + phone + "] is not a valid number"
            );
        }

    }

    private boolean isPhoneNumberValid(String s) {
        Pattern pattern = Pattern.compile("(?:\\+254)(7(?:(?:[9][0-9])|(?:[8][0-9])|(?:[7][0-9])|(?:[6][0-9])|(?:[5][0-9])|(?:[4][0-8])|(?:[3][0-9])|(?:[2][0-9])|(?:[1][0-9])|([0][0-9]))[0-9]{6})");
        Matcher match = pattern.matcher(s);
        return (match.find() && match.group().equals(s));
    }
}

