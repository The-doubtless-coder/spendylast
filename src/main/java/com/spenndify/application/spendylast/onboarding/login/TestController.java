package com.spenndify.application.spendylast.onboarding.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tester")
public class TestController {
    @GetMapping("/1")
    public String testToken(){
        return "will not work";
    }
}
