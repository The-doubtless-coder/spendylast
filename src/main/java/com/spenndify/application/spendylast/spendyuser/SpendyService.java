package com.spenndify.application.spendylast.spendyuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

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
        SpendyUser user = spendyRepository.findByEmailOrPhone(emailOrPhone);
        if(user==null){
            throw new UsernameNotFoundException(String.format(response, emailOrPhone));
        }
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }


    public String signUpSpendyUser(SpendyUser spendyUser) throws EntityExistsException {
        Boolean userPresent = spendyRepository.findByIdNumber(spendyUser.getIdNumber()).isPresent();
        //todo: check for phone or email; however, in the db these values are unique
        //todo: change exceptions to illegal state exceptions
        if(userPresent){
            throw new EntityExistsException("User exists! use NON-REGISTERED credentials. " +
                    "Either email, phone or id is reused");
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(spendyUser.getPassword());
        spendyUser.setPassword(passwordEncoded);
        //todo: send twilio message
        //todo: then activate or enable aaccount
        spendyRepository.save(spendyUser);
        return "Tis done comrade, User successfully registered!";
    }
}
