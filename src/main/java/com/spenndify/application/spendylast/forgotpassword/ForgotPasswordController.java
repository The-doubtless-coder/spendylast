package com.spenndify.application.spendylast.forgotpassword;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
