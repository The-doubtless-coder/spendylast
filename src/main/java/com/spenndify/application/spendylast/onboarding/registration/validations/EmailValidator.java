package com.spenndify.application.spendylast.onboarding.registration.validations;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String p) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";//regex expression
        Pattern pattern = Pattern.compile(regex);//compile regular expression to get the pattern
        Matcher matcher = pattern.matcher(p);//create instance of matcher
        return matcher.matches();
    }
}
