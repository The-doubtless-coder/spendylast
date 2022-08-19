package com.spenndify.application.spendylast.onboarding.Twilio.verifyotp;

import com.spenndify.application.spendylast.onboarding.registration.validations.PhoneValidator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/spendy/user")
@CrossOrigin(origins="*")
public class VerifyController {

    private final VerifyService verifyService;
    private final PhoneValidator phoneValidator;

    @PostMapping("/verify/registration/otp")
    public ResponseEntity<String> verifyRegistrationOtp(@RequestBody @NotNull VerifyRequest verifyRequest) throws Exception {
        Boolean isPhoneValid = phoneValidator.test(verifyRequest.getPhone());
        if(!isPhoneValid){
            throw new IllegalStateException("The number provided is invalid!");
        }
        return verifyService.compareToStoredOtp(verifyRequest);
    }
}
