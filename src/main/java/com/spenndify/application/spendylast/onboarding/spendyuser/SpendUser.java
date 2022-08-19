package com.spenndify.application.spendylast.onboarding.spendyuser;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "spenndify_user", uniqueConstraints = {@UniqueConstraint(columnNames
        = "NationId", name = "spenndify_user_national_id_unique"),
@UniqueConstraint(columnNames = "Email", name = "spenndify_user_email_unique"),
@UniqueConstraint(columnNames = "Telephone", name = "spenndify_user_phone_unique")})
public class SpendUser implements UserDetails {
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @Column(name= "first_name", nullable = false)
    @NotBlank(message = "name cannot be null")
    private String firstName;
    @Column(name= "last_name", nullable = false)
    @NotBlank(message = "surname cannot be null")
    private String lastName;
    @Column(name= "NationId", nullable = false)
    @NotBlank(message = "must input value")
    private String idNumber;
    @Column(name= "Email", nullable = false)
    @Email(message = "invalid address provided")
    @NotBlank
    private String email;
    @Column(name= "Telephone", nullable = false)
    private String phone;
    @Column(name= "q1", nullable = false)
    private String questionOne;
    @Column(name= "q2", nullable = false)
    private String questionTwo;
    @Column(name= "q3", nullable = false)
    private String questionThree;
    @Column(name= "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private SpendyRoles userRole;
    private Boolean locked = false;
    private Boolean enabled = true;
    //TODO: ENSURE YOU USE THE ACCOUNT LOCKED AND ENABLED FEATURES
    //TODO: USE TWILLIO TO SEND OTP TO EMAIL FOR WEB
    //TODO: MAP SECURITY QUESTIONS USING MANY TO MANY (INPUT ON WEB)

    public SpendUser(String firstName, String lastName, String idNumber, String email, String phone, String questionOne, String questionTwo, String questionThree, String password, SpendyRoles userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.phone = phone;
        this.questionOne = questionOne;
        this.questionTwo = questionTwo;
        this.questionThree = questionThree;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
