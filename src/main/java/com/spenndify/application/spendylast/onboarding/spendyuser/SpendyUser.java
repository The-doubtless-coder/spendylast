package com.spenndify.application.spendylast.onboarding.spendyuser;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name= "spendy_user")
public class SpendyUser implements UserDetails {
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
    private String firstName;
    @Column(name= "last_name", nullable = false)
    private String lastName;
    @Column(name= "NationId", nullable = false, unique = true)
    private String idNumber;
    @Column(name= "Email", nullable = false, unique = true)
    private String email;
    @Column(name= "Telephone", nullable = false, unique = true)
    private String phone;
    @Column(name= "q1", nullable = false)
    private String questionOne;//change to many to many
    @Column(name= "q2", nullable = false)
    private String questionTwo;//change to many to many
    @Column(name= "q3", nullable = false)
    private String questionThree;//change to many to many
    @Column(name= "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private SpendyRoles userRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    //TODO: ENSURE YOU USE THE ACCOUNT LOCKED AND ENABLED FEATURES
    //TODO: USE TWILLIO TO SEND OTP TO EMAIL FOR WEB
    //TODO: MAP SECURITY QUESTIONS USING MANY TO MANY (INPUT ON WEB)

    public SpendyUser(String firstName, String lastName, String idNumber, String email, String phone, String questionOne, String questionTwo, String questionThree, String password, SpendyRoles userRole) {
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
