package com.example.Rowdyback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF protection
                .authorizeRequests() // Authorize requests
                .anyRequest().permitAll() // Permit all requests
                .and()
                .httpBasic().disable() // Disable HTTP Basic authentication
                .formLogin().disable() // Disable form login
                .logout().disable(); // Disable logout

        return http.build();
    }
}
