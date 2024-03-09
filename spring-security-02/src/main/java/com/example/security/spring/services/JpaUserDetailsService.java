package com.example.security.spring.services;

import com.example.security.spring.entities.User;
import com.example.security.spring.repositories.UserRepository;
import com.example.security.spring.models.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public JpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findUserByUsername(username);
        return optionalUser.map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));

    }
}
