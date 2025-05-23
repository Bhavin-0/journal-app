package com.journal.journalapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs (especially when using Postman or frontend apps)
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints that don't require authentication
                        .requestMatchers("/auth/register", "/journal/entries").permitAll()
                        // All other endpoints require authentication
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults -> {}) // Enable Basic Auth
                .build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
