package com.spenndify.application.spendylast.onboarding.login.anotherpath;

import com.spenndify.application.spendylast.onboarding.Twilio.TwilioSmsSender;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtpService;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyRepository;
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
    public ResponseEntity<ResponseExists> lookForUser(PhoneRequest phoneRequest) throws Exception{
        SpendUser user = spendyRepository.findByPhone(phoneRequest.getPhone());
        if(user==null){
            throw new Exception("User is not registered with us, slide to the registration page");
        }
//        return new ResponseEntity<>("User is registered with us, slide to verify otp", HttpStatus.OK);
        SpendUser user1 = spendyRepository.findByEmailOrPhone(phoneRequest.getPhone());
        String fullname = user1.getFullname();

        ResponseExists responseExists = new ResponseExists("User exists, move to enter pin screen", fullname);
        return new ResponseEntity<>(responseExists, HttpStatus.OK);
    }
}
