package com.example.security.spring.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class TenantFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tenantId = request.getHeader("X-Tenant-Id");
        if (isUserAllowed(tenantId)) {
            filterChain.doFilter(request, response);
            return;
        }
        throw new AccessDeniedException("Access Denied");
    }

    private boolean isUserAllowed(String tenantId){
        return "TheLordOfTheRings".equals(tenantId);
    }
}
