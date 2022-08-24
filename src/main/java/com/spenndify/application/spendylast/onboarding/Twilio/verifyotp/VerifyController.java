package com.spenndify.application.spendylast.onboarding.Twilio.verifyotp;

import com.spenndify.application.spendylast.categories.config.ApiResponse;
import com.spenndify.application.spendylast.onboarding.registration.validations.PhoneValidator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/spendy/user")
public class VerifyController {

    private final VerifyService verifyService;
    private final PhoneValidator phoneValidator;

    @PostMapping("/verify/registration/otp")
    public ResponseEntity<ApiResponse> verifyRegistrationOtp(@RequestBody @NotNull VerifyRequest verifyRequest) throws Exception {
        Boolean isPhoneValid = phoneValidator.test(verifyRequest.getPhone());
        if(!isPhoneValid){
            return new ResponseEntity<>( new ApiResponse(false, "verification failed, phone is not valid"), HttpStatus.BAD_REQUEST);
        }
        return verifyService.compareToStoredOtp(verifyRequest);
    }
}
