package com.ssafy.webrtc.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 스프링 시큐리티 방지
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.httpBasic().disable();

        return security.build();
    }

}