package com.spenndify.application.spendylast.onboarding.Twilio;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/spendy/user")
@AllArgsConstructor
public class SendController {

    private final TwilioSmsSender twilioSmsSender;
    @PostMapping("/send/otp")
    public ResponseEntity<String> sendSms(@RequestBody @NotNull SendRequest sendRequest) {
        twilioSmsSender.sendSms(sendRequest);
        return new ResponseEntity<>("Otp sent successfully", HttpStatus.OK);
    }
}
