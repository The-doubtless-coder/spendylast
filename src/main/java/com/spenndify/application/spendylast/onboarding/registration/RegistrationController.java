package com.spenndify.application.spendylast.onboarding.registration;

import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InvalidPropertiesFormatException;

@RestController
@RequestMapping("/spendy/user")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping("/registration") //todo:return to string
    public ResponseEntity<SpendUser> registerUser(@RequestBody RegistrationRequest registrationRequest)
            throws InvalidPropertiesFormatException {
        return registrationService.register(registrationRequest);
    }
}
