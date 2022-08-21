package com.spenndify.application.spendylast.onboarding.login.anotherpath;

import com.spenndify.application.spendylast.onboarding.Twilio.TwilioSmsSender;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtpService;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyRepository;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneService {
    private final SpendyRepository spendyRepository;
    private final GeneratedOtpService generatedOtpService;
    private final TwilioSmsSender twilioSmsSender;
    public ResponseEntity<String> lookForUser(PhoneRequest phoneRequest) throws Exception{
        SpendUser user = spendyRepository.findByPhone(phoneRequest.getPhone());
        if(user==null){
            throw new Exception("User is not registered with us, slide to the registration page");
        }
        return new ResponseEntity<>("User is registered with us, slide to verify otp", HttpStatus.OK);
    }
}
