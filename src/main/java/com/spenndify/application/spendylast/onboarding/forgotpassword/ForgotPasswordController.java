package com.spenndify.application.spendylast.onboarding.forgotpassword;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/spendy/user")
@AllArgsConstructor
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    @PutMapping("/password/reset")
    public ResponseEntity<String> resetAccountPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        return forgotPasswordService.checkIdValidateQuestions(forgotPasswordRequest);
    }
}
