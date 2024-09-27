package com.ga5000.Clinic.security;

import com.ga5000.Clinic.services.CustomUserDetailsServiceImpl;
import com.ga5000.Clinic.services.TokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenServiceImpl tokenServiceImpl;
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    public SecurityFilter(TokenServiceImpl tokenServiceImpl, CustomUserDetailsServiceImpl customUserDetailsService) {
        this.tokenServiceImpl = tokenServiceImpl;
        this.customUserDetailsService = customUserDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);
        if (token != null) {
            var email = tokenServiceImpl.extractUsername(token);
            if (email != null) {
                UserDetails user = customUserDetailsService.loadUserByUsername(email);
                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new RuntimeException("User with email: " + email + " wasn't found");
                }
            } else {
                throw new RuntimeException("Invalid token");
            }
        }
        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer: ", "");
    }
}
