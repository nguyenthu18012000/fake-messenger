package com.main.fakeMessenger.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private static final List<String> PATHS_NO_AUTH = List.of(
            "/*/login",
            "/auth/register",
            "/swagger-ui.html",
            "/swagger-ui/**"
    );

    private final JwtTokenVerifier jwtTokenVerifier;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity, HttpServletRequest request)
            throws Exception {

        String[] pathsNoAuth = new String[PATHS_NO_AUTH.size()];
        PATHS_NO_AUTH.toArray(pathsNoAuth);

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(pathsNoAuth).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((requesta, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("Unauthorized: Token missing or invalid");
                        })
                );
        return httpSecurity.build();
    }
}
