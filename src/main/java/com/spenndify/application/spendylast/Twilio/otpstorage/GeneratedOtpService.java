package com.spenndify.application.spendylast.Twilio.otpstorage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GeneratedOtpService {

    private final GeneratedOtpRepository generatedOtpRepository;

    public void saveGeneratedOtp(GeneratedOtp generatedOtp) {
        generatedOtpRepository.save(generatedOtp);
    }

    public Optional<GeneratedOtp> getOtp(String otp) {
        return generatedOtpRepository.findByOtp(otp);
    }
}
