package com.spenndify.application.spendylast.onboarding.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String idNumber;
    private final String email;
    private final String phone;
    private final String questionOne;//change to mnay to many
    private final String questionTwo;//change to many to many
    private final String questionThree;//change to many to many
    private final String password;
}
