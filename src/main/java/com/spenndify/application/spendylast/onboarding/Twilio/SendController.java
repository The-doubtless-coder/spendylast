package com.spenndify.application.spendylast.onboarding.Twilio;

import com.spenndify.application.spendylast.categories.config.ApiResponse;
import com.spenndify.application.spendylast.onboarding.registration.validations.PhoneValidator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spendy/user")
@AllArgsConstructor
public class SendController {

    private final TwilioSmsSender twilioSmsSender;
    private final PhoneValidator phoneValidator;
    @PostMapping("/send/otp")
    public ResponseEntity<ApiResponse> sendSms(@RequestBody @NotNull SendRequest sendRequest) {
        Boolean isValid = phoneValidator.test(sendRequest.getPhone());
        if(isValid){
        twilioSmsSender.sendSms(sendRequest);
            return new ResponseEntity<>( new ApiResponse(true, "Otp sent successfully"), HttpStatus.OK);
        }else
        return new ResponseEntity<>( new ApiResponse(false, "phone is invalid! Otp not Sent!"), HttpStatus.BAD_REQUEST);
    }
}

