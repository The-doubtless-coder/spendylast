package com.spenndify.application.spendylast.spendyuser;

import com.spenndify.application.spendylast.Twilio.TwilioSmsSender;
import com.spenndify.application.spendylast.Twilio.otpstorage.GeneratedOtp;
import com.spenndify.application.spendylast.Twilio.otpstorage.GeneratedOtpService;
import lombok.AllArgsConstructor;
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
import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class SpendyService implements UserDetailsService {
    private final SpendyRepository spendyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TwilioSmsSender twilioSmsSender;
    private final GeneratedOtpService generatedOtpService;
    private final static String response = "User with Username %s is non-existent. " +
            "Kindly Check your credentials and try again";

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        //TODO: FOR IF ELSE CREATE REPO THAT RETURNS USER NOT OPTIONAL THEN RETURN USER
//        return spendyRepository.findByEmailOrPhone(emailOrPhone)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format(response, emailOrPhone)));
        SpendyUser user = spendyRepository.findByEmailOrPhone(emailOrPhone);
        if(user==null){
            throw new UsernameNotFoundException(String.format(response, emailOrPhone));
        }
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }


    public ResponseEntity<String> signUpSpendyUser(SpendyUser spendyUser) throws EntityExistsException {
        Boolean userPresent = spendyRepository.findByPhone(spendyUser.getPhone()).isPresent();
        //todo: check for phone or email; however, in the db these values are unique
        //todo: change exceptions to illegal state exceptions
        if(userPresent){
            throw new EntityExistsException("User exists! use NON-REGISTERED credentials. " +
                    "Either email, phone or id is reused");
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(spendyUser.getPassword());
        spendyUser.setPassword(passwordEncoded);
        spendyRepository.save(spendyUser);

        String otp = generateOTP();
        GeneratedOtp generatedOtp = new GeneratedOtp(otp,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5), spendyUser);
        generatedOtpService.saveGeneratedOtp(generatedOtp);
        String message = "Buda Boss! Otp is " + otp + ". Itumie kuregista spenndify";
        twilioSmsSender.sendSms(spendyUser.getPhone(), message);
        return new ResponseEntity<>("Tis done comrade, User successfully registered!", HttpStatus.OK);
    }
    private String generateOTP() {
        return new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
    }
    public void enableSpendyUser(String phone) {
        spendyRepository.enableSpendyUser(phone);
    }

    public void changeSpendyUserPassword(String password, String idNumber){
        spendyRepository.changeUserPassword(password, idNumber);
    }
}
