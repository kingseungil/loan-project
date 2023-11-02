package com.zb.loanproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
          .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers("/test/**").permitAll()
            .requestMatchers("/error").permitAll()
            .anyRequest().authenticated()
          )
          .csrf(AbstractHttpConfigurer::disable)
          .formLogin(AbstractHttpConfigurer::disable)
          .httpBasic(AbstractHttpConfigurer::disable)
          .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          )
          .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
