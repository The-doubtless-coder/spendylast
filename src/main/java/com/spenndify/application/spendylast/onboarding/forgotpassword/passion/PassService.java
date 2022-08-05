package com.spenndify.application.spendylast.onboarding.forgotpassword.passion;

import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PassService {
    private SpendyRepository spendyRepository;
    public ResponseEntity<String> comparePassion(PassionRequest passionRequest) throws Exception {
        SpendUser user = spendyRepository.findByPhone(passionRequest.getPhone());
        if(user==null){
            throw new Exception("user does not exist");
        }
        if(user.getQuestionOne().equals(passionRequest.getPassion().toLowerCase()))
            return new ResponseEntity<>("Details are correct", HttpStatus.OK);
        else
            return new ResponseEntity<>("wrong details provided", HttpStatus.CONFLICT);
    }
}
