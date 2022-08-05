package com.spenndify.application.spendylast.onboarding.registration;

import com.spenndify.application.spendylast.onboarding.registration.validations.PhoneValidator;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyRoles;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import com.spenndify.application.spendylast.onboarding.registration.validations.EmailValidator;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyService;
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
    private final String response = "Email %s is invalid!, Recheck and try again";
    private final String response_1 = "Phone %s is invalid!, Recheck and try again";
    public ResponseEntity<SpendUser> register(RegistrationRequest registrationRequest) throws InvalidPropertiesFormatException {
        boolean emailValid = emailValidator.test(registrationRequest.getEmail());
        boolean phoneValid = phoneValidator.test(registrationRequest.getPhone());
        if(!emailValid){
            throw new InvalidPropertiesFormatException(String.format(response, registrationRequest.getEmail()));
        }
        if(!phoneValid){
            throw new InvalidPropertiesFormatException(String.format(response_1, registrationRequest.getPhone()));
        }
        return spendyService.signUpSpendyUser(new SpendUser(registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getIdNumber(),
                registrationRequest.getEmail(),
                registrationRequest.getPhone(),
                registrationRequest.getQuestionOne().toLowerCase(),
                registrationRequest.getQuestionTwo().toLowerCase(),
                registrationRequest.getQuestionThree().toLowerCase(),
                registrationRequest.getPassword(),
                SpendyRoles.USER));
    }


}
