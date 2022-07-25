package com.spenndify.application.spendylast.onboarding.Twilio.otpstorage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface GeneratedOtpRepository
        extends JpaRepository<GeneratedOtp, Long> {
    GeneratedOtp findByOtp(String otp);
}
