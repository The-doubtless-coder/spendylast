package com.spenndify.application.spendylast.onboarding.login.anotherpath;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuth {
    private String user;
    private String token;
}
