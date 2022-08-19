package com.spenndify.application.spendylast.onboarding.Twilio.otpstorage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "otps", uniqueConstraints = {
        @UniqueConstraint(columnNames = "phone", name = "user_phone_unique")})
public class GeneratedOtp {

    @SequenceGenerator(
            name = "generated_otp_sequence",
            sequenceName = "generated_otp_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "generated_otp_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private String phone;

    public GeneratedOtp(String otp, LocalDateTime createdAt, LocalDateTime expiresAt, String phone) {
        this.otp = otp;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.phone = phone;
    }
}
