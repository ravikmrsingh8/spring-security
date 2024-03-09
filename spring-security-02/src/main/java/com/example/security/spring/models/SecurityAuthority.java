package com.example.security.spring.models;

import com.example.security.spring.entities.Authority;
import org.springframework.security.core.GrantedAuthority;

public class SecurityAuthority implements GrantedAuthority {

    private final Authority authority;

    public SecurityAuthority(Authority authority){
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
