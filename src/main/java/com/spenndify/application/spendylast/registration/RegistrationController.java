package com.spenndify.application.spendylast.registration;

import com.spenndify.application.spendylast.spendyuser.SpendyUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InvalidPropertiesFormatException;

@RestController
@RequestMapping("/spendify/user/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping //todo:return to string
    public String registerUser(@RequestBody RegistrationRequest registrationRequest)
            throws InvalidPropertiesFormatException {
        return registrationService.register(registrationRequest);
    }
    //TODO: RETURN AN ENTITY PLUS A STRING OR HTTP STATUS (FADATARE)
}
