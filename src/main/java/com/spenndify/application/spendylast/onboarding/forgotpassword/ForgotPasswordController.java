package com.spenndify.application.spendylast.onboarding.forgotpassword;

import com.spenndify.application.spendylast.onboarding.registration.validations.PhoneValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spendy/user")
@AllArgsConstructor
@CrossOrigin(origins="*")
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;
    private PhoneValidator phoneValidator;

    @PutMapping("/password/reset")
    public ResponseEntity<String> resetAccountPassword
            (@RequestBody ForgotPasswordRequest forgotPasswordRequest) throws Exception{
        boolean isValid = phoneValidator.test(forgotPasswordRequest.getPhone());
        if(!isValid){
            throw new Exception("Enter genuine number");
        }
        return forgotPasswordService.checkIdValidateQuestions(forgotPasswordRequest);
    }
}
