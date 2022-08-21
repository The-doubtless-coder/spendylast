package com.spenndify.application.spendylast.onboarding.forgotpassword.passion;

import com.spenndify.application.spendylast.onboarding.registration.validations.PhoneValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/spendy/user")
public class PassController {
    private PassService passService;
    private PhoneValidator phoneValidator;
    @PostMapping("/passion")
    public ResponseEntity<String> checkPassion(@RequestBody PassionRequest passionRequest) throws Exception {
        boolean isValid = phoneValidator.test(passionRequest.getPhone());
        if(!isValid){
            throw new Exception("enter a genuine number");
        }
        return passService.comparePassion(passionRequest);
    }
}
