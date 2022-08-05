package com.spenndify.application.spendylast.onboarding.Twilio.otpstorage;

import com.spenndify.application.spendylast.onboarding.spendyuser.SpendUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
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

//    @ManyToOne
//    @JoinColumn(
//            nullable = false,
//            name = "spendy_user_id"
//    )
//    private SpendUser spendUser;

    public GeneratedOtp(String otp, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.otp = otp;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
