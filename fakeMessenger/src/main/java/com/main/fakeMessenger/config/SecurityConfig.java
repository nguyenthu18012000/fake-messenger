package com.main.fakeMessenger.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    private static final List<String> PATHS_NO_AUTH = List.of("/*/login", "auth/register");

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity, HttpServletRequest request)
            throws Exception {

        String[] pathsNoAuth = new String[PATHS_NO_AUTH.size()];
        PATHS_NO_AUTH.toArray(pathsNoAuth);

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(pathsNoAuth).permitAll()
                .anyRequest().authenticated()
        );
        return httpSecurity.build();
    }
}
