package com.example.security.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        // requestMatcher should be configured first before anyRequest
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/precious").hasAuthority("Precious")
                .requestMatchers("/api/bonjour").hasAuthority("FrenchGreet")
                .requestMatchers("/api/**").hasAuthority("Read")
                .anyRequest().authenticated()
        );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1 = User.withUsername("ravi")
                .password(passwordEncoder().encode("password"))
                .authorities("Read", "Write", "Precious")
                .build();
        UserDetails user2 = User.withUsername("bill")
                .password(passwordEncoder().encode("password"))
                .authorities("Read")
                .build();
        UserDetails user3 = User.withUsername("macron")
                .password(passwordEncoder().encode("password"))
                .authorities("Read", "FrenchGreet")
                .build();
        UserDetails user4 = User.withUsername("dud")
                .password(passwordEncoder().encode("password"))
                .build();

        return new InMemoryUserDetailsManager(List.of(user1, user2, user3, user4));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
