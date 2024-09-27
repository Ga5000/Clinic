package com.ga5000.Clinic.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String generateToken(UserDetails user);
    String extractUsername(String token);
}
