package com.spenndify.application.spendylast.onboarding.Twilio;

import com.spenndify.application.spendylast.onboarding.Twilio.Config.TwilioConfiguration;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtp;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtpRepository;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtpService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

@Service("twilio")
@AllArgsConstructor
public class TwilioSmsSender{

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;
    private final GeneratedOtpService generatedOtpService;
    private final GeneratedOtpRepository generatedOtpRepository;

    public void sendSms(SendRequest sendRequest) throws IllegalStateException{
//        if (isPhoneNumberValid(sendRequest.getPhone())){
            PhoneNumber to = new PhoneNumber(sendRequest.getPhone());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());

            GeneratedOtp ifPhoneExists = generatedOtpRepository.findByPhone(sendRequest.getPhone());
            if(ifPhoneExists!=null){
                generatedOtpRepository.deleteById(ifPhoneExists.getId());
            }

            String otp = generateOTP();

            GeneratedOtp generatedOtp = new GeneratedOtp(otp,
                LocalDateTime.now(),
                LocalDateTime.now().plusSeconds(60),
                    sendRequest.getPhone());
            generatedOtpService.saveGeneratedOtp(generatedOtp);

            String message = "Your OTP is " + otp + ". Itumie kuregista spenndify!";

            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", sendRequest);
//        } else {
//            throw new IllegalStateException(
//                    "Phone number [" + sendRequest.getPhone() + "] is not a valid number, start with +254"
//            );
        }

//    private boolean isPhoneNumberValid(String s) {
//        Pattern pattern = Pattern.compile("(?:\\+254)(7(?:(?:[9][0-9])|(?:[8][0-9])|(?:[7][0-9])|(?:[6][0-9])|(?:[5][0-9])|(?:[4][0-8])|(?:[3][0-9])|(?:[2][0-9])|(?:[1][0-9])|([0][0-9]))[0-9]{6})");
//        Matcher match = pattern.matcher(s);
//        return (match.find() && match.group().equals(s));
//    }
    public String generateOTP(){
        return new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
    }
}

