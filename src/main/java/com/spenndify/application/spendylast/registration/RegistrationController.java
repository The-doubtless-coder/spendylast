package com.spenndify.application.spendylast.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.InvalidPropertiesFormatException;

@RestController
@RequestMapping("/spendy/user")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping("/registration") //todo:return to string
    public String registerUser(@RequestBody RegistrationRequest registrationRequest)
            throws InvalidPropertiesFormatException {
        return registrationService.register(registrationRequest);
    }
    //TODO: RETURN AN ENTITY PLUS A STRING OR HTTP STATUS (FADATARE)
}
