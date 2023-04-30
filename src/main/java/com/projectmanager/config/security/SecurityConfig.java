package com.projectmanager.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig  {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(ROLE_ADMIN)
                .build();

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("user"))
                .roles(ROLE_USER)
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/project").hasAnyRole(ROLE_USER, ROLE_ADMIN)
                .requestMatchers(HttpMethod.POST, "/project").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.PUT, "/project/{id:\\d+}").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/project/{id:\\d+}").hasRole(ROLE_ADMIN)
                .anyRequest().denyAll()
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}