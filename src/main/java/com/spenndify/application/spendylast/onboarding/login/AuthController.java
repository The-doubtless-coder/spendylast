package com.spenndify.application.spendylast.onboarding.login;

import com.spenndify.application.spendylast.onboarding.jwts.JwtUtil;
import com.spenndify.application.spendylast.onboarding.login.anotherpath.ResponseAuth;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spendy/user")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SpendyRepository spendyRepository;

    @GetMapping("/")
    public String myHomepage(){
        return "Hoooooreeeei! Spendify";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseAuth> generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        }catch(Exception exception){
            throw new Exception("Wrong login credentials provided, " +
                "Kindly recheck and try again");
        }
        //todo: unlock account

        SpendUser spender = spendyRepository.findByEmailOrPhone(authRequest.getUserName());
        String fullname = spender.getFullname();

        String token = jwtUtil.generateToken(authRequest.getUserName());
        ResponseAuth data = new ResponseAuth(fullname, token);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
