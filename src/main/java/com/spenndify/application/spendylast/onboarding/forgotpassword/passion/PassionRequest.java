package com.spenndify.application.spendylast.onboarding.forgotpassword.passion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassionRequest {

    private String phone;
    private String passion;
}
