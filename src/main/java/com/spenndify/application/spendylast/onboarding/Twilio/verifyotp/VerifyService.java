package com.spenndify.application.spendylast.onboarding.Twilio.verifyotp;

import com.spenndify.application.spendylast.categories.config.ApiResponse;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtp;
import com.spenndify.application.spendylast.onboarding.Twilio.otpstorage.GeneratedOtpRepository;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VerifyService {
    private GeneratedOtpRepository generatedOtpRepository;
    private final SpendyService spendyService;
    public ResponseEntity<ApiResponse> compareToStoredOtp(VerifyRequest verifyRequest) throws Exception {
        GeneratedOtp findUserOtp = generatedOtpRepository.findByPhone(verifyRequest.getPhone());
        if (findUserOtp == null) {
            throw new Exception("Otp was never sent to user with phone [" +
                    verifyRequest.getPhone() + "], send him/her an otp and try again.");
        }

//        if (findUserOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
//            throw new Exception("Otp is already expired! send another one");
//        }

        if (findUserOtp.getOtp().equals(verifyRequest.getReceivedOtp())) {
            if (findUserOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
                throw new Exception("Otp is already expired! send another one");
            }
            return new ResponseEntity<>( new ApiResponse(true,"Otp successfully verified, i.e correct code provided"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(false,"Otp verification failed!, i.e wrong otp code provided"), HttpStatus.CONFLICT);
        }
    }
}
