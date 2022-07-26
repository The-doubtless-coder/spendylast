package com.spenndify.application.spendylast.onboarding.login.anotherpath;

import com.spenndify.application.spendylast.onboarding.Twilio.TwilioSmsSender;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtp;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtpService;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyRepository;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyService;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class PhoneService {
    private final SpendyRepository spendyRepository;
    private final GeneratedOtpService generatedOtpService;
    private final TwilioSmsSender twilioSmsSender;
    public ResponseEntity<String> lookForUser(PhoneRequest phoneRequest) throws Exception{
        SpendyUser user = spendyRepository.findByPhone(phoneRequest.getPhone());
        if(user==null){
            throw new Exception("User is not registered with us, slide to the registration page");
        }
//        String otp = generateOTP();
//        GeneratedOtp generatedOtp = new GeneratedOtp(otp,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusDays(5), user);
//        generatedOtpService.saveGeneratedOtp(generatedOtp);
//        String message = "Buda Boss! Otp is " + otp + ". Endelea na login";
//        twilioSmsSender.sendSms(phoneRequest.getPhone(), message);
        return new ResponseEntity<>("User is registered with us, slide to verify otp", HttpStatus.OK);
    }
//    public String generateOTP(){
//        return new DecimalFormat("0000")
//                .format(new Random().nextInt(9999));
//    }
}
