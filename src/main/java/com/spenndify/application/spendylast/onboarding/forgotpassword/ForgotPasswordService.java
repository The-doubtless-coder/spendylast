package com.spenndify.application.spendylast.onboarding.forgotpassword;

import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyRepository;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyService;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ForgotPasswordService {
    private final SpendyRepository spendyRepository;
    private final SpendyService spendyService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    public ResponseEntity<String> checkIdValidateQuestions(@NotNull ForgotPasswordRequest forgotPasswordRequest)
            throws IllegalStateException{
        SpendUser spendUser = spendyRepository.findByIdNumber(forgotPasswordRequest.getIdNumber());
        if(spendUser ==null){
            throw new IllegalStateException("User does not exist!");
        }
        if(spendUser.getQuestionOne().equals(forgotPasswordRequest.getResponseToSecurityQuestion())||
                spendUser.getQuestionTwo().equals(forgotPasswordRequest.getResponseToSecurityQuestion())||
                spendUser.getQuestionThree().equals(forgotPasswordRequest.getResponseToSecurityQuestion())){
            spendyService.changeSpendyUserPassword(bCryptPasswordEncoder
                    .encode(forgotPasswordRequest.getNewPassword()), forgotPasswordRequest.getIdNumber());
            return new ResponseEntity<>("password changed successfully", HttpStatus.OK);
        }else
            return new ResponseEntity<>("password reset failed!, wrong answer to security question provided",
                    HttpStatus.BAD_REQUEST);
    }
}
