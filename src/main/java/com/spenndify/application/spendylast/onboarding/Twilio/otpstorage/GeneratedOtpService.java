package com.spenndify.application.spendylast.onboarding.Twilio.otpstorage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GeneratedOtpService {

    private final GeneratedOtpRepository generatedOtpRepository;

    public void saveGeneratedOtp(GeneratedOtp generatedOtp) {
        generatedOtpRepository.save(generatedOtp);
    }

    public Optional<GeneratedOtp> getOtp(String otp) {
        return Optional.ofNullable(generatedOtpRepository.findByOtp(otp));
    }
}
