package com.spenndify.application.spendylast.onboarding.spendyuser;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.text.DecimalFormat;
import java.util.Random;

@Service
@AllArgsConstructor
public class SpendyService implements UserDetailsService {
    private final SpendyRepository spendyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String response = "User with Username %s is non-existent. " +
            "Kindly Check your credentials and try again";

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        //TODO: FOR IF ELSE CREATE REPO THAT RETURNS USER NOT OPTIONAL THEN RETURN USER
//        return spendyRepository.findByEmailOrPhone(emailOrPhone)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format(response, emailOrPhone)));
        SpendUser user = spendyRepository.findByEmailOrPhone(emailOrPhone);
        if(user==null){
            throw new UsernameNotFoundException(String.format(response, emailOrPhone));
        }
//        return new SpendUser(user);
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }


    public ResponseEntity<SpendUser> signUpSpendyUser(SpendUser spendUser) throws EntityExistsException {
        SpendUser userPresent = spendyRepository.findByPhone(spendUser.getPhone());
        //todo: check for phone or email; however, in the db these values are unique
        //todo: change exceptions to illegal state exceptions
        if(userPresent!=null){
            throw new EntityExistsException("User exists! use NON-REGISTERED credentials. " +
                    "Either email, phone or id is reused");
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(spendUser.getPassword());
        spendUser.setPassword(passwordEncoded);
        spendyRepository.save(spendUser);
        return new ResponseEntity<>(spendUser, HttpStatus.OK);
    }
    private @NotNull String generateOTP() {
        return new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
    }
    public void enableSpendyUser(String phone) {
        spendyRepository.enableSpendyUser(phone);
    }

    public void changeSpendyUserPassword(String password, String phone){
        spendyRepository.changeUserPassword(password, phone);
    }
}
