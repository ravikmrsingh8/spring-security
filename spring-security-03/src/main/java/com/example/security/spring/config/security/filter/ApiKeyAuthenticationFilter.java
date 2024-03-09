 package com.example.security.spring.config.security.filter;

import com.example.security.spring.config.security.auth.ApiKeyAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authManager;

    public ApiKeyAuthenticationFilter(AuthenticationManager authManager){
        this.authManager = authManager;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Create an Authentication Object which is not yet authenticated
        // 2. Delegate to Authentication Manager to authenticate
        // 3. Get back Authentication from the manager
        // 4. if authenticate proceed with next filter chain
        String authKey = request.getHeader("key");
        Authentication auth = new ApiKeyAuthentication(authKey,false);

        Authentication authentication = authManager.authenticate(auth);
        if(authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }

    }
}
