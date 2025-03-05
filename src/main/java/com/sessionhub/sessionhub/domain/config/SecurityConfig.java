package com.sessionhub.sessionhub.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보안 비활성화 (API 서버일 경우)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/signUp", "/auth/logIn", "/auth/session")
                        .permitAll() // 회원가입, 로그인은 인증 없이 허용
                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .expiredUrl("/login")
                );

        return http.build();
    }

    }
