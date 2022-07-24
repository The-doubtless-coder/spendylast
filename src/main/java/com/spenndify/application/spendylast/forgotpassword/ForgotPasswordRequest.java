package com.spenndify.application.spendylast.forgotpassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequest {
    private String idNumber;
    private String responseToSecurityQuestion;
    private String newPassword;
}
