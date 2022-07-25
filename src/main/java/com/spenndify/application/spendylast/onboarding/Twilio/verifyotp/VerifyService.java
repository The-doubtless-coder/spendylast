package com.spenndify.application.spendylast.onboarding.Twilio.verifyotp;

import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtp;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtpRepository;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VerifyService {
    private GeneratedOtpRepository generatedOtpRepository;
    private final SpendyService spendyService;
    @Transactional
    public ResponseEntity<String> compareToStoredOtp( VerifyRequest verifyRequest) throws Exception {
        GeneratedOtp retrievedOtp = generatedOtpRepository.findByOtp(verifyRequest.getReceivedOtp());
        if (retrievedOtp == null) {
            throw new Exception("Otp not available in the spendy database");
        }

        if (retrievedOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new Exception("Otp is already expired! send another one");
        }

        if (retrievedOtp.getOtp().equals(verifyRequest.getReceivedOtp())) {
            spendyService.enableSpendyUser(retrievedOtp.getSpendyUser().getPhone());
            return new ResponseEntity<>("Otp successfully verified, now set password", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Otp verification failed!, please try again "
                    + verifyRequest.getReceivedOtp(), HttpStatus.CONFLICT);
        }
    }
}
