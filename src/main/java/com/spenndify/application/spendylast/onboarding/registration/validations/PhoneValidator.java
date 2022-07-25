package com.spenndify.application.spendylast.onboarding.registration.validations;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        Pattern pattern = Pattern.compile("(?:\\+254)(7(?:(?:[9][0-9])|(?:[8][0-9])|(?:[7][0-9])|(?:[6][0-9])|(?:[5][0-9])|(?:[4][0-8])|(?:[3][0-9])|(?:[2][0-9])|(?:[1][0-9])|([0][0-9]))[0-9]{6})");//(0/91): number starts with (0/91)
        //[7-9]: starting of the number may contain a digit between 0 to 9
        //[0-9]: then contains digits 0 to 9
        Matcher match = pattern.matcher(s);
        return (match.find() && match.group().equals(s));
    }
}