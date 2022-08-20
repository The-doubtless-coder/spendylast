package com.spenndify.application.spendylast.onboarding.Twilio.verifyotp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyRequest {
    private String receivedOtp;
    private String phone;
}
