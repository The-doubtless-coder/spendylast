package com.spenndify.application.spendylast.onboarding.websecurity.configsec;

import com.spenndify.application.spendylast.onboarding.jwts.JwtFilter;
import com.spenndify.application.spendylast.onboarding.spendyuser.SpendyService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SpendyService spendyService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtFilter jwtFilter;//enable filter and register filter

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/spendy/user/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//register policy
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;//register filter
    }
    @Override//accepts the authentication provider
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(spendyService);//get username and password from the service
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

