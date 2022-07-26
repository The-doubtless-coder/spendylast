package com.spenndify.application.spendylast.onboarding.login;

import com.spenndify.application.spendylast.onboarding.jwts.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/spendy/user")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String myHomepage(){
        return "Hoooooreeeei! Spendify";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        }catch(Exception exception){
            throw new Exception("Wrong login credentials provided, " +
                "Kindly recheck and try again");
        }
        //todo: unlock account

        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
