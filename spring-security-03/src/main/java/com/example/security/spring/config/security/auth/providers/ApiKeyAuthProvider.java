package com.example.security.spring.config.security.auth.providers;

import com.example.security.spring.config.security.auth.ApiKeyAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthProvider implements AuthenticationProvider {

    @Value("${server.api.secret.key}")
    private String authKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication apiKeyAuth = (ApiKeyAuthentication) authentication;
        if (authKey.equals(apiKeyAuth.getAuthKey())) {
            return new ApiKeyAuthentication("", true);
        }
        throw new BadCredentialsException("Invalid Api Key");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
