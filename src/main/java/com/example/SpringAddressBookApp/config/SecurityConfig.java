package com.example.SpringAddressBookApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> new org.springframework.web.cors.CorsConfiguration().applyPermitDefaultValues()))
                // Disable CORS completely
                // Enable CORS with credentials
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/auth/**", "/auth/register", "/auth/login", "/api/contacts/**").permitAll()  // Allow H2 console and auth endpoints
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(config -> config.disable()));  // Allow H2 frames
        return http.build();
    }
}
