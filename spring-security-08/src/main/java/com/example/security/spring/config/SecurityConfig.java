package com.example.security.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/***
 * Enables preAuthorize, postAuthorize, preFilter, postFilter
 * preAuthorize - Authorize before method execution
 * postAuthorize - Authorize after method executed base on returned data
 * preFilter - Filter request body objects before method object
 * postFilter - Filter returned data from method
 */

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
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
                .authorities("Read", "French")
                .build();
        UserDetails user4 = User.withUsername("Rango")
                .password(passwordEncoder().encode("password"))
                .authorities("Read", "KillRattlesnake")
                .build();

        return new InMemoryUserDetailsManager(List.of(user1, user2, user3, user4));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
