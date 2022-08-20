package com.spenndify.application.spendylast.onboarding.login.anotherpath;

import com.spenndify.application.spendylast.onboarding.registration.validations.PhoneValidator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spendy/user")
@AllArgsConstructor
//@CrossOrigin(origins="*")
public class ExistController {

    private final PhoneService phoneService;
    private final PhoneValidator phoneValidator;
    @PostMapping("/exist")
    public ResponseEntity<String> doesUserExist(@RequestBody @NotNull PhoneRequest phoneRequest) throws Exception {
        Boolean legit = phoneValidator.test(phoneRequest.getPhone());
        if (!legit){
            throw new IllegalStateException("the phone number provided is not Genuine!");
        }
        return phoneService.lookForUser(phoneRequest);
    }
}
