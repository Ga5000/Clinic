package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.entities.Person;

public interface TokenService {
    String generateToken(Person person);
    String extractUsername(String token);
}
