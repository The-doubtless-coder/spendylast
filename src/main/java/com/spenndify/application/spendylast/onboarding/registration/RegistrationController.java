package com.spenndify.application.spendylast.onboarding.registration;

import com.spenndify.application.spendylast.categories.config.ApiResponse;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InvalidPropertiesFormatException;

@RestController
@RequestMapping("/spendy/user")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping("/registration") //todo:return to string
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegistrationRequest registrationRequest)
            throws InvalidPropertiesFormatException {
        ResponseEntity<SpendUser> savedUser = registrationService.register(registrationRequest);
        return new ResponseEntity<ApiResponse>( new
                ApiResponse(true, "User has been successfully registered!"), HttpStatus.ACCEPTED);
    }

}
