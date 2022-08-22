package com.spenndify.application.spendylast.onboarding.login;

import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserOnDashboard {

    private final SpendyRepository spendyRepository;
    @GetMapping("/fullname")
    public String getAuthUserInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String phoneOrEmail = auth.getName();

        SpendUser user = spendyRepository.findByEmailOrPhone(phoneOrEmail);
        return user.getFullname();
    }
}
