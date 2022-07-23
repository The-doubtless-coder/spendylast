package com.spenndify.application.spendylast.registration;

import com.spenndify.application.spendylast.registration.validations.EmailValidator;
import com.spenndify.application.spendylast.registration.validations.PhoneValidator;
import com.spenndify.application.spendylast.spendyuser.SpendyRoles;
import com.spenndify.application.spendylast.spendyuser.SpendyService;
import com.spenndify.application.spendylast.spendyuser.SpendyUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.InvalidPropertiesFormatException;

@Service
@AllArgsConstructor
public class RegistrationService {
    private EmailValidator emailValidator;
    private PhoneValidator phoneValidator;
    private SpendyService spendyService;
    private final String response = "Email %s or Phone %s is invalid!, Recheck and try again";
    public String register(RegistrationRequest registrationRequest) throws InvalidPropertiesFormatException {
        Boolean emailValid = emailValidator.test(registrationRequest.getEmail());
        Boolean phoneValid = phoneValidator.test(registrationRequest.getPhone());
        if(!emailValid||!phoneValid){
            throw new InvalidPropertiesFormatException(String.format(response, registrationRequest.getEmail(),
                    registrationRequest.getPhone()));
        }
        return spendyService.signUpSpendyUser(new SpendyUser(registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getIdNumber(),
                registrationRequest.getEmail(),
                registrationRequest.getPhone(),
                registrationRequest.getQuestionOne(),
                registrationRequest.getQuestionTwo(),
                registrationRequest.getQuestionThree(),
                registrationRequest.getPassword(),
                SpendyRoles.USER));
    }


}
